package com.example.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.settlement.model.SettlementReceipt;

@Repository
public interface SettlementReceiptRepository
        extends JpaRepository<SettlementReceipt, Long> {}
