package com.example.settlement.exception;

import lombok.Getter;

@Getter
public class SettlementException extends RuntimeException {

    private final SettlementErrorCode errorCode;

    public SettlementException(SettlementErrorCode errorCode) {
        super(errorCode.getDefaultDescription());
        this.errorCode = errorCode;
    }

    public SettlementException(SettlementErrorCode errorCode, Throwable cause) {
        super(errorCode.getDefaultDescription(), cause);
        this.errorCode = errorCode;
    }

    public SettlementException(SettlementErrorCode errorCode, String message) {
        super(message != null ? message : errorCode.getDefaultDescription());
        this.errorCode = errorCode;
    }
}
