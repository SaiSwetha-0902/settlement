package com.example.settlement.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
@Entity
@Table(name = "settlement_receipt")
public class SettlementReceipt {

    public Long getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Long receiptId) {
		this.receiptId = receiptId;
	}

	public Parent getParentId() {
		return parentId;
	}

	public void setParentId(Parent parentId) {
		this.parentId = parentId;
	}

	public Long getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(Long distributorId) {
		this.distributorId = distributorId;
	}

	public Long getNseceId() {
		return nseceId;
	}

	public void setNseceId(Long nseceId) {
		this.nseceId = nseceId;
	}

	public Long getFundHouseId() {
		return fundHouseId;
	}

	public void setFundHouseId(Long fundHouseId) {
		this.fundHouseId = fundHouseId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getNettedId() {
		return nettedId;
	}

	public void setNettedId(Long nettedId) {
		this.nettedId = nettedId;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;

    @Enumerated(EnumType.STRING)
    private Parent parentId; // who initiated cash flow

    private Long distributorId;
    private Long nseceId;
    private Long fundHouseId;

    private BigDecimal amount;
    private Long nettedId;

    public enum Parent {
        NSECE, FUND_HOUSE
    }
}
