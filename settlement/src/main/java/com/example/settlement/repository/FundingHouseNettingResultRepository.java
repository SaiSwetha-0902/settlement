package com.example.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.model.FundingHouseNettingResult;

@Repository
public interface FundingHouseNettingResultRepository
        extends JpaRepository<FundingHouseNettingResult, Long>,
                SettlementInstructionRepository<FundingHouseNettingResult> {

    @Override
    @Transactional
    @Query(value = """
        SELECT * FROM funding_house_netting_result
        WHERE status = 'CALCULATED'
        AND (lease_expiry IS NULL OR lease_expiry < NOW())
        ORDER BY settlement_date
        LIMIT 1
        FOR UPDATE SKIP LOCKED
        """, nativeQuery = true)
    FundingHouseNettingResult findNextForSettlement();
}
