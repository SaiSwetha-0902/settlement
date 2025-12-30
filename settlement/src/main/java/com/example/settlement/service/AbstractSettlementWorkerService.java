package com.example.settlement.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.exception.InsufficientBalanceException;
import com.example.settlement.exception.InvalidSettlementStateException;
import com.example.settlement.model.NettedOutput;
import com.example.settlement.model.SettlementInstruction;
import com.example.settlement.repository.SettlementInstructionRepository;
import com.example.settlement.utils.LeaseUtil;

public abstract class AbstractSettlementWorkerService<T extends SettlementInstruction> {

    protected final SettlementInstructionRepository<T> repo;
    protected final CashMovementService cashService;
    protected final ReceiptLedgerService receiptService;
    protected final LeaseUtil leaseUtil;

    protected AbstractSettlementWorkerService(
            SettlementInstructionRepository<T> repo,
            CashMovementService cashService,
            ReceiptLedgerService receiptService,
            LeaseUtil leaseUtil
    ) {
        this.repo = repo;
        this.cashService = cashService;
        this.receiptService = receiptService;
        this.leaseUtil = leaseUtil;
    }

    public void process(String brokerNodeId) {

        T instruction = claimForSettlement(brokerNodeId);
        if (instruction == null) return;

        try {
            cashService.moveCash(instruction);
            receiptService.generateReceiptAndLedger(instruction);
            markSettled(instruction.getId());

        } catch (InsufficientBalanceException e) {
            markFailed(instruction.getId());

        } catch (Exception e) {
            markFailed(instruction.getId());
            throw e;
        }
    }

    @Transactional
    protected T claimForSettlement(String brokerNodeId) {

        T instruction = repo.findNextForSettlement();
        if (instruction == null) return null;
        // Idempotency check: if already SETTLED or FAILED, skip it
        if (instruction.getStatus() == NettedOutput.Status.SETTLED ||
            instruction.getStatus() == NettedOutput.Status.FAILED) {
            return null;
        }

        if (instruction.getStatus() != NettedOutput.Status.NETTED) {
            throw new InvalidSettlementStateException(
                "Invalid state: " + instruction.getStatus()
            );
        }

        instruction.setStatus(NettedOutput.Status.INITIATED);
        instruction.setBrokerNodeId(brokerNodeId);
        instruction.setLeaseId(UUID.randomUUID().toString());
        instruction.setLeaseExpiry(
            LocalDateTime.now().plusSeconds(leaseUtil.getLeaseSeconds())
        );

        return repo.save(instruction);
    }

    @Transactional
    protected void markSettled(Long id) {
        T instruction = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found: " + id));
        instruction.setStatus(NettedOutput.Status.SETTLED);
        repo.save(instruction);
    }

    @Transactional
    protected void markFailed(Long id) {
        T instruction = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found: " + id));
        instruction.setStatus(NettedOutput.Status.FAILED);
        repo.save(instruction);
    }
}
