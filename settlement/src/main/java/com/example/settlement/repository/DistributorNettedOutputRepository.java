package com.example.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.model.DistributorNettedOutput;

@Repository
public interface DistributorNettedOutputRepository
        extends JpaRepository<DistributorNettedOutput, Long>,
                SettlementInstructionRepository<DistributorNettedOutput> {

    @Override
    @Transactional
    @Query(value = """
        SELECT * FROM distributor_netted_output
        WHERE status = 'NETTED'
        AND (lease_expiry IS NULL OR lease_expiry < NOW())
        ORDER BY id
        LIMIT 1
        FOR UPDATE SKIP LOCKED
        """, nativeQuery = true)
    DistributorNettedOutput findNextForSettlement();
}
