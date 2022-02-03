package com.museum.user.backend.repositories;

import com.museum.user.backend.model.entities.MuseumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MuseumEntityRepository extends JpaRepository<MuseumEntity, Integer> {
}
