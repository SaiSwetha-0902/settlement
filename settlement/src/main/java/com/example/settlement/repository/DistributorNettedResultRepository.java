package com.example.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.model.DistributorNettedResult;

@Repository
public interface DistributorNettedResultRepository
        extends JpaRepository<DistributorNettedResult, Long>,
                SettlementInstructionRepository<DistributorNettedResult> {

    @Override
    @Transactional
    @Query(value = """
        SELECT * FROM distributor_netted_result
        WHERE status = 'NETTED'
        AND (lease_expiry IS NULL OR lease_expiry < NOW())
        ORDER BY id
        LIMIT 1
        FOR UPDATE SKIP LOCKED
        """, nativeQuery = true)
    DistributorNettedResult findNextForSettlement();
}
