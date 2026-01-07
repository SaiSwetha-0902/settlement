package com.example.settlement.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.model.CashAccount;
import com.example.settlement.model.FundingHouseNettingResult;
import com.example.settlement.model.SettlementInstruction;
import com.example.settlement.repository.CashAccountRepository;

@Service
public class CashMovementService {

    private final CashAccountRepository accountRepo;

    public CashMovementService(CashAccountRepository accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Transactional
    public void moveCash(SettlementInstruction instruction) {

        CashAccount nse =
            accountRepo.findByOwnerTypeAndOwnerId(
                CashAccount.OwnerType.NSECE,
                instruction.getNseceId()
            );

        CashAccount participant =
            accountRepo.findByOwnerTypeAndOwnerId(
                CashAccount.OwnerType.valueOf(
                    instruction.getParticipantType().name()
                ),
                instruction.getParticipantId()
            );

        BigDecimal amt = instruction.getAmount();

        if (instruction.getType() ==  FundingHouseNettingResult.Type.PAY_IN) {
            participant.debit(amt);
            nse.credit(amt);
        } else {
            nse.debit(amt);
            participant.credit(amt);
        }

        accountRepo.save(participant);
        accountRepo.save(nse);
    }
}