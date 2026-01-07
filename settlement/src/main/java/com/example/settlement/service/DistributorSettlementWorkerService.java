package com.example.settlement.service;

import org.springframework.stereotype.Service;

import com.example.settlement.model.DistributorNettedResult;
import com.example.settlement.repository.DistributorNettedResultRepository;
import com.example.settlement.utils.LeaseUtil;

@Service
public class DistributorSettlementWorkerService
        extends AbstractSettlementWorkerService<DistributorNettedResult> {

    public DistributorSettlementWorkerService(
    		DistributorNettedResultRepository repo,
            CashMovementService cashService,
            ReceiptLedgerService receiptService,
            LeaseUtil leaseUtil
    ) {
        super(repo, cashService, receiptService, leaseUtil);
    }
}
