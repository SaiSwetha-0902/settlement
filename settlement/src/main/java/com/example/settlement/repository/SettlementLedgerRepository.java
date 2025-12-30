package com.example.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.settlement.model.SettlementLedger;

@Repository
public interface SettlementLedgerRepository
        extends JpaRepository<SettlementLedger, Long> {}
