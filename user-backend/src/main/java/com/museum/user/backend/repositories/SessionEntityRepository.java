package com.museum.user.backend.repositories;

import com.museum.user.backend.model.entities.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionEntityRepository extends JpaRepository<SessionEntity, Integer> {
    Optional<SessionEntity> findByUsername(String username);
    void deleteByUsername(String username);
}
