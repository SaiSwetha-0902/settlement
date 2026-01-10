package com.example.settlement.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.settlement.model.FundingHouseNettingResult.Status;
import com.example.settlement.model.SettlementInstruction;

public interface SettlementInstructionRepository<T extends SettlementInstruction> {

    T findNextForSettlement();

	List<T> findByStatusAndLeaseExpiryBefore(Status initiated, LocalDateTime now);

	
}
