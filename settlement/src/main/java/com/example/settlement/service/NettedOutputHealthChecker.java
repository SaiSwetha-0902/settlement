package com.example.settlement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.model.FundingHouseNettingResult;
import com.example.settlement.model.SettlementInstruction;
import com.example.settlement.repository.FundingHouseNettingResultRepository;

@Service
public class NettedOutputHealthChecker {

    private final FundingHouseNettingResultRepository repo;

    public NettedOutputHealthChecker(FundingHouseNettingResultRepository repo) {
        this.repo = repo;
    }

    @Scheduled(fixedDelay = 10000) // check every second
    @Transactional
    public void checkExpiredInstructions() {
        LocalDateTime now = LocalDateTime.now();
        List<FundingHouseNettingResult> expired = repo.findByStatusAndLeaseExpiryBefore(FundingHouseNettingResult.Status.INITIATED, now);

        for (FundingHouseNettingResult instruction : expired) {
            System.out.println("Lease expired, reverting FundingHouseNettingResult: " + instruction.getId());
            instruction.setStatus(FundingHouseNettingResult.Status.FAILED);
            instruction.setLeaseId(null);
            instruction.setLeaseExpiry(null);
            repo.save(instruction);
        }
    }
}
