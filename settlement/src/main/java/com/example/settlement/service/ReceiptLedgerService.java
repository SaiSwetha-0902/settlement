package com.example.settlement.service;

import org.springframework.stereotype.Service;

import com.example.settlement.model.FundingHouseNettingResult;
import com.example.settlement.model.SettlementInstruction;
import com.example.settlement.model.SettlementLedger;
import com.example.settlement.model.SettlementReceipt;
import com.example.settlement.repository.SettlementLedgerRepository;
import com.example.settlement.repository.SettlementReceiptRepository;

@Service
public class ReceiptLedgerService {

    private final SettlementReceiptRepository receiptRepo;
    private final SettlementLedgerRepository ledgerRepo;

    public ReceiptLedgerService(
            SettlementReceiptRepository receiptRepo,
            SettlementLedgerRepository ledgerRepo
    ) {
        this.receiptRepo = receiptRepo;
        this.ledgerRepo = ledgerRepo;
    }

    public void generateReceiptAndLedger(SettlementInstruction instruction) {

        // --- Validation ---
        if (instruction.getType() == null) {
            throw new IllegalStateException("Settlement type cannot be null");
        }
        if (instruction.getNseceId() == null || instruction.getParticipantId() == null) {
            throw new IllegalStateException("Participants missing");
        }

        // --- Create Receipt ---
        SettlementReceipt receipt = new SettlementReceipt();
        receipt.setAmount(instruction.getAmount());
        receipt.setNettedId(instruction.getId());
        receipt.setNseceId(instruction.getNseceId());

        // 🔑 Fill participant correctly
        switch (instruction.getParticipantType()) {
            case FUND_HOUSE -> receipt.setFundHouseId(instruction.getParticipantId());
            case DISTRIBUTOR -> receipt.setDistributorId(instruction.getParticipantId());
        }

        // Parent = who initiated cash flow
        if (instruction.getType() == FundingHouseNettingResult.Type.PAY_IN) {
            receipt.setParentId(
                instruction.getParticipantType() == SettlementInstruction.ParticipantType.FUND_HOUSE
                    ? SettlementReceipt.Parent.FUND_HOUSE
                    : SettlementReceipt.Parent.NSECE   // distributor pays → NSECE receives
            );
        } else {
            receipt.setParentId(SettlementReceipt.Parent.NSECE);
        }

        receipt = receiptRepo.save(receipt);

        // --- Ledger (Double Entry) ---
        SettlementLedger ledger = new SettlementLedger();
        ledger.setReceiptId(receipt.getReceiptId());
        ledger.setAmount(instruction.getAmount());

        if (instruction.getType() == FundingHouseNettingResult.Type.PAY_IN) {
            ledger.setDebitAccount(instruction.getParticipantId());
            ledger.setCreditAccount(instruction.getNseceId());
        } else {
            ledger.setDebitAccount(instruction.getNseceId());
            ledger.setCreditAccount(instruction.getParticipantId());
        }

        ledgerRepo.save(ledger);
    }
}