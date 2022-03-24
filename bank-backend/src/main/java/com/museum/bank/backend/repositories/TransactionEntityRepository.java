package com.museum.bank.backend.repositories;

import com.museum.bank.backend.model.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Integer> {
}
