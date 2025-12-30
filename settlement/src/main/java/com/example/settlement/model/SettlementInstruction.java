package com.example.settlement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface SettlementInstruction {

    Long getId();

    NettedOutput.Type getType();

    BigDecimal getAmount();

    Long getNseceId();

    // Generic counter-party (FundHouse / Distributor)
    Long getParticipantId();
    ParticipantType getParticipantType();

    NettedOutput.Status getStatus();

    // Required for generic settlement worker
    void setStatus(NettedOutput.Status status);
    void setBrokerNodeId(String brokerNodeId);
    void setLeaseId(String leaseId);
    void setLeaseExpiry(LocalDateTime leaseExpiry);

    enum ParticipantType {
        FUND_HOUSE,
        DISTRIBUTOR
    }
}
