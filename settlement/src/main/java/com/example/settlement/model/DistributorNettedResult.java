package com.example.settlement.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "distributor_netted_result")
public class DistributorNettedResult implements SettlementInstruction {

    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private FundingHouseNettingResult.Type type;

    private BigDecimal amount;

    private Long nseceId;
    private Long distributorId;

    @Enumerated(EnumType.STRING)
    private FundingHouseNettingResult.Status status;

    private String brokerNodeId;
    private String leaseId;
    private LocalDateTime leaseExpiry;

    // ---------- Interface implementations ----------

    @Override
    public Long getId() { return id; }

    @Override
    public FundingHouseNettingResult.Type getType() { return type; }

    @Override
    public BigDecimal getAmount() { return amount; }

    @Override
    public Long getNseceId() { return nseceId; }

    @Override
    public Long getParticipantId() { return distributorId; }

    @Override
    public ParticipantType getParticipantType() {
        return ParticipantType.DISTRIBUTOR;
    }

    @Override
    public FundingHouseNettingResult.Status getStatus() { return status; }

    // ---------- setters ----------

    public void setId(Long id) { this.id = id; }
    public void setType(FundingHouseNettingResult.Type type) { this.type = type; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setNseceId(Long nseceId) { this.nseceId = nseceId; }
    public void setDistributorId(Long distributorId) { this.distributorId = distributorId; }

    @Override
    public void setStatus(FundingHouseNettingResult.Status status) { this.status = status; }

    @Override
    public void setBrokerNodeId(String brokerNodeId) { this.brokerNodeId = brokerNodeId; }

    @Override
    public void setLeaseId(String leaseId) { this.leaseId = leaseId; }

    @Override
    public void setLeaseExpiry(LocalDateTime leaseExpiry) { this.leaseExpiry = leaseExpiry; }
}
