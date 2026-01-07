package com.example.settlement.exception;

import lombok.Getter;

@Getter
public enum SettlementErrorCode {

    ST001("HIGH", "Invalid settlement state"),
    ST002("HIGH", "Insufficient balance for settlement"),
    ST003("MEDIUM", "Settlement validation failed");

    private final String severity;
    private final String defaultDescription;

    SettlementErrorCode(String severity, String defaultDescription) {
        this.severity = severity;
        this.defaultDescription = defaultDescription;
    }
}
