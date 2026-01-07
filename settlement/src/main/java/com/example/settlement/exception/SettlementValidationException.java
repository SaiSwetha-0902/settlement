package com.example.settlement.exception;

public class SettlementValidationException extends SettlementException {

    public SettlementValidationException(String message) {
        super(SettlementErrorCode.ST003, message);
    }
}
