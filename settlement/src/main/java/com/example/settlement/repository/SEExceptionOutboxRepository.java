package com.example.settlement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.settlement.model.SEExceptionOutbox;

@Repository
public interface SEExceptionOutboxRepository
        extends JpaRepository<SEExceptionOutbox, UUID> {

   
}
