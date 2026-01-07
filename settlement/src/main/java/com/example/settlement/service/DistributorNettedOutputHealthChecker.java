package com.example.settlement.service;

import java.time.LocalDateTime;

import com.example.settlement.model.FundingHouseNettingResult;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.model.DistributorNettedResult;
import com.example.settlement.model.DistributorNettedResult;
import com.example.settlement.repository.DistributorNettedResultRepository;

@Service
public class DistributorNettedOutputHealthChecker {

    private final DistributorNettedResultRepository repo;

    public DistributorNettedOutputHealthChecker(DistributorNettedResultRepository repo) {
        this.repo = repo;
    }

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void checkExpiredInstructions() {
        LocalDateTime now = LocalDateTime.now();
        List<DistributorNettedResult> expired = repo.findByStatusAndLeaseExpiryBefore(FundingHouseNettingResult.Status.INITIATED, now);

        for (DistributorNettedResult instruction : expired) {
            System.out.println("Lease expired, reverting DistributorNettedOutput: " + instruction.getId());
            instruction.setStatus(FundingHouseNettingResult.Status.FAILED);
            instruction.setLeaseId(null);
            instruction.setLeaseExpiry(null);
            repo.save(instruction);
        }
    }
}
