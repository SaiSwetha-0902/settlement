package com.example.settlement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.settlement.model.CashAccount;
import com.example.settlement.model.CashAccount.OwnerType;

@Repository
public interface CashAccountRepository extends JpaRepository<CashAccount, Long> {
    CashAccount findByOwnerType(CashAccount.OwnerType ownerType);

	CashAccount findByOwnerTypeAndOwnerId(OwnerType mapOwnerType, Long participantId);
}
