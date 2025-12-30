package com.example.settlement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.settlement.service.FundHouseSettlementWorkerService;
import com.example.settlement.service.DistributorSettlementWorkerService;

@RestController
@RequestMapping("/test")
public class SettlementTestController {

    private final FundHouseSettlementWorkerService fundHouseWorker;
    private final DistributorSettlementWorkerService distributorWorker;

    public SettlementTestController(
            FundHouseSettlementWorkerService fundHouseWorker,
            DistributorSettlementWorkerService distributorWorker
    ) {
        this.fundHouseWorker = fundHouseWorker;
        this.distributorWorker = distributorWorker;
    }

    @PostMapping("/run/fundhouse")
    public String runFundHouseSettlement() {
        fundHouseWorker.process("test-broker");
        return "Fund house settlement run completed";
    }

    @PostMapping("/run/distributor")
    public String runDistributorSettlement() {
        distributorWorker.process("test-broker");
        return "Distributor settlement run completed";
    }
}
