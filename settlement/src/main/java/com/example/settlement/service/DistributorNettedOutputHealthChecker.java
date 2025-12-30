package com.example.settlement.service;

import java.time.LocalDateTime;

import com.example.settlement.model.NettedOutput;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.model.DistributorNettedOutput;
import com.example.settlement.repository.DistributorNettedOutputRepository;

@Service
public class DistributorNettedOutputHealthChecker {

    private final DistributorNettedOutputRepository repo;

    public DistributorNettedOutputHealthChecker(DistributorNettedOutputRepository repo) {
        this.repo = repo;
    }

    @Scheduled(fixedDelay = 10000)
    @Transactional
    public void checkExpiredInstructions() {
        LocalDateTime now = LocalDateTime.now();
        List<DistributorNettedOutput> expired = repo.findByStatusAndLeaseExpiryBefore(NettedOutput.Status.INITIATED, now);

        for (DistributorNettedOutput instruction : expired) {
            System.out.println("Lease expired, reverting DistributorNettedOutput: " + instruction.getId());
            instruction.setStatus(NettedOutput.Status.FAILED);
            instruction.setLeaseId(null);
            instruction.setLeaseExpiry(null);
            repo.save(instruction);
        }
    }
}
