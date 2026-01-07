package com.example.settlement.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.settlement.service.DistributorSettlementWorkerService;
import com.example.settlement.service.FundHouseSettlementWorkerService;

import java.util.concurrent.ExecutorService;

@Component
public class SettlementBatchScheduler {

    private final FundHouseSettlementWorkerService fundHouseWorker;
    private final DistributorSettlementWorkerService distributorWorker;

    private final ExecutorService fundHouseExecutor;
    private final ExecutorService distributorExecutor;

    public SettlementBatchScheduler(
            FundHouseSettlementWorkerService fundHouseWorker,
            DistributorSettlementWorkerService distributorWorker,
            ExecutorService fundHouseExecutor,
            ExecutorService distributorExecutor
    ) {
        this.fundHouseWorker = fundHouseWorker;
        this.distributorWorker = distributorWorker;
        this.fundHouseExecutor = fundHouseExecutor;
        this.distributorExecutor = distributorExecutor;
    }

    @Scheduled(fixedDelay = 10000) 
    public void runFundHouseSettlement() {
        for (int i = 0; i < 5; i++) { 
            fundHouseExecutor.submit(() -> fundHouseWorker.process("BROKER_NODE_1"));
        }
    }

    @Scheduled(fixedDelay = 10000)
    public void runDistributorSettlement() {
        for (int i = 0; i < 5; i++) {
            distributorExecutor.submit(() -> distributorWorker.process("BROKER_NODE_1"));
        }
    }
}
