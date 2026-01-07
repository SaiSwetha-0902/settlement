package com.example.settlement.exception;

public class InsufficientBalanceException extends SettlementException {

    public InsufficientBalanceException() {
        super(SettlementErrorCode.ST002);
    }

    public InsufficientBalanceException(String message) {
        super(SettlementErrorCode.ST002, message);
    }
}
