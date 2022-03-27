package com.museum.user.backend.repositories;

import com.museum.user.backend.model.entities.LogsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsEntityRepository extends JpaRepository<LogsEntity, Integer> {
}
