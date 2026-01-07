package com.example.settlement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface SettlementInstruction {

    Long getId();

    FundingHouseNettingResult.Type getType();

    BigDecimal getAmount();

    Long getNseceId();

    // Generic counter-party (FundHouse / Distributor)
    Long getParticipantId();
    ParticipantType getParticipantType();

    FundingHouseNettingResult.Status getStatus();

    // Required for generic settlement worker
    void setStatus(FundingHouseNettingResult.Status status);
    void setBrokerNodeId(String brokerNodeId);
    void setLeaseId(String leaseId);
    void setLeaseExpiry(LocalDateTime leaseExpiry);

    enum ParticipantType {
        FUND_HOUSE,
        DISTRIBUTOR
    }
}
