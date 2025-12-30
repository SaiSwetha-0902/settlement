package com.example.settlement.exception;

public class InsufficientBalanceException extends SettlementException {

    public InsufficientBalanceException() {
        super("Insufficient balance for settlement");
    }
}
