package com.example.settlement.service;

import com.example.settlement.exception.SettlementException;
import com.example.settlement.model.SEExceptionOutbox;
import com.example.settlement.repository.SEExceptionOutboxRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class ExceptionOutboxService {

 private final ObjectMapper objectMapper = new ObjectMapper();
    private final SEExceptionOutboxRepository repository;

    public ExceptionOutboxService(SEExceptionOutboxRepository repository) {
        this.repository = repository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(SettlementException ex, Long id) {

        try {
            String payload = objectMapper.writeValueAsString(
                Map.of(
                    "instruction_id", id,
                    "error_code", ex.getErrorCode().name(),
                    "severity", ex.getErrorCode().getSeverity(),
                    "description", ex.getMessage()
                )
            );

            SEExceptionOutbox entity = SEExceptionOutbox.builder()
                    .id(UUID.randomUUID())
                    .payload(payload)
                    .status("PENDING")
                    .build();

            repository.save(entity);

        } catch (Exception e) {

            log.error("Failed to persist exception outbox", e);
        }
    }
}
