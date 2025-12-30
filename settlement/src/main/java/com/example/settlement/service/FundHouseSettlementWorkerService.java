package com.example.settlement.service;

import org.springframework.stereotype.Service;

import com.example.settlement.model.NettedOutput;
import com.example.settlement.repository.NettedOutputRepository;
import com.example.settlement.utils.LeaseUtil;

@Service
public class FundHouseSettlementWorkerService
        extends AbstractSettlementWorkerService<NettedOutput> {

    public FundHouseSettlementWorkerService(
    		NettedOutputRepository repo,
            CashMovementService cashService,
            ReceiptLedgerService receiptService,
            LeaseUtil leaseUtil
    ) {
        super(repo, cashService, receiptService, leaseUtil);
    }
}
