package com.example.settlement.utils;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Utility to handle lease creation, expiry, and checks.
 * Ensures safe claim of netted rows by multiple workers.
 */
@Component
public class LeaseUtil {

    @Value("${settlement.lease.duration-seconds:30}")
    private long leaseSeconds; // default 30 seconds if not configured

    /**
     * Returns lease expiry datetime
     */
    public LocalDateTime calculateExpiry() {
        return LocalDateTime.now().plusSeconds(leaseSeconds);
    }

    /**
     * Check if lease is expired
     */
    public boolean isExpired(LocalDateTime leaseExpiry) {
        return leaseExpiry != null && leaseExpiry.isBefore(LocalDateTime.now());
    }

    /**
     * Optional: Get configured lease seconds
     */
    public long getLeaseSeconds() {
        return leaseSeconds;
    }
}
