package com.museum.user.backend.repositories;

import com.museum.user.backend.model.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourEntityRepository extends JpaRepository<TourEntity, Integer> {

}
