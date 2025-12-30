package com.example.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.settlement.model.NettedOutput;

@Repository
public interface NettedOutputRepository
        extends JpaRepository<NettedOutput, Long>,
                SettlementInstructionRepository<NettedOutput> {

    @Override
    @Transactional
    @Query(value = """
        SELECT * FROM netted_output
        WHERE status = 'NETTED'
        AND (lease_expiry IS NULL OR lease_expiry < NOW())
        ORDER BY settlement_date
        LIMIT 1
        FOR UPDATE SKIP LOCKED
        """, nativeQuery = true)
    NettedOutput findNextForSettlement();
}
