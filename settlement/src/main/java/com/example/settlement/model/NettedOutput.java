package com.example.settlement.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "netted_output")
public class NettedOutput implements SettlementInstruction {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private BigDecimal amount;
    private LocalDate settlementDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long nseceId;
    private Long fundHouseId;

    private String brokerNodeId;
    private String leaseId;
    private LocalDateTime leaseExpiry;

    // ---------- Interface implementations ----------

    @Override
    public Long getId() { return id; }

    @Override
    public Type getType() { return type; }

    @Override
    public BigDecimal getAmount() { return amount; }

    @Override
    public Long getNseceId() { return nseceId; }

    @Override
    public Long getParticipantId() { return fundHouseId; }

    @Override
    public ParticipantType getParticipantType() {
        return ParticipantType.FUND_HOUSE;
    }

    @Override
    public Status getStatus() { return status; }

    // ---------- setters ----------

    public void setId(Long id) { this.id = id; }
    public void setType(Type type) { this.type = type; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setSettlementDate(LocalDate settlementDate) { this.settlementDate = settlementDate; }

    @Override
    public void setStatus(Status status) { this.status = status; }

    public void setNseceId(Long nseceId) { this.nseceId = nseceId; }
    public void setFundHouseId(Long fundHouseId) { this.fundHouseId = fundHouseId; }

    @Override
    public void setBrokerNodeId(String brokerNodeId) { this.brokerNodeId = brokerNodeId; }

    @Override
    public void setLeaseId(String leaseId) { this.leaseId = leaseId; }

    @Override
    public void setLeaseExpiry(LocalDateTime leaseExpiry) { this.leaseExpiry = leaseExpiry; }

    // ---------- enums ----------
    public enum Type { PAY_IN, PAY_OUT }
    public enum Status { NETTED, INITIATED, SETTLED, FAILED }
}
