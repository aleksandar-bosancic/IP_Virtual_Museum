package com.museum.user.backend.repositories;

import com.museum.user.backend.model.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionEntityRepository extends JpaRepository<SessionEntity, Integer> {
}
