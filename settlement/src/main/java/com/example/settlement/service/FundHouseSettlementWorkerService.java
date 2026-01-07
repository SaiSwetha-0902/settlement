package com.example.settlement.service;

import org.springframework.stereotype.Service;

import com.example.settlement.model.FundingHouseNettingResult;
import com.example.settlement.repository.FundingHouseNettingResultRepository;
import com.example.settlement.utils.LeaseUtil;

@Service
public class FundHouseSettlementWorkerService
        extends AbstractSettlementWorkerService<FundingHouseNettingResult> {

    public FundHouseSettlementWorkerService(
    		FundingHouseNettingResultRepository repo,
            CashMovementService cashService,
            ReceiptLedgerService receiptService,
            LeaseUtil leaseUtil
    ) {
        super(repo, cashService, receiptService, leaseUtil);
    }
}
