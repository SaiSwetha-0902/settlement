package com.example.settlement.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.model.NettedOutput;
import com.example.settlement.model.SettlementInstruction;
import com.example.settlement.repository.NettedOutputRepository;

@Service
public class NettedOutputHealthChecker {

    private final NettedOutputRepository repo;

    public NettedOutputHealthChecker(NettedOutputRepository repo) {
        this.repo = repo;
    }

    @Scheduled(fixedDelay = 10000) // check every second
    @Transactional
    public void checkExpiredInstructions() {
        LocalDateTime now = LocalDateTime.now();
        List<NettedOutput> expired = repo.findByStatusAndLeaseExpiryBefore(NettedOutput.Status.INITIATED, now);

        for (NettedOutput instruction : expired) {
            System.out.println("Lease expired, reverting NettedOutput: " + instruction.getId());
            instruction.setStatus(NettedOutput.Status.FAILED);
            instruction.setLeaseId(null);
            instruction.setLeaseExpiry(null);
            repo.save(instruction);
        }
    }
}
