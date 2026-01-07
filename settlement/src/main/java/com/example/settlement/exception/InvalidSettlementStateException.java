package com.example.settlement.exception;

public class InvalidSettlementStateException extends SettlementException {

    public InvalidSettlementStateException(String message) {
        super(SettlementErrorCode.ST001, message);
    }
}
