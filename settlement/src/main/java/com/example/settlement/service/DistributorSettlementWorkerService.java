package com.example.settlement.service;

import org.springframework.stereotype.Service;

import com.example.settlement.model.DistributorNettedOutput;
import com.example.settlement.repository.DistributorNettedOutputRepository;
import com.example.settlement.utils.LeaseUtil;

@Service
public class DistributorSettlementWorkerService
        extends AbstractSettlementWorkerService<DistributorNettedOutput> {

    public DistributorSettlementWorkerService(
    		DistributorNettedOutputRepository repo,
            CashMovementService cashService,
            ReceiptLedgerService receiptService,
            LeaseUtil leaseUtil
    ) {
        super(repo, cashService, receiptService, leaseUtil);
    }
}
