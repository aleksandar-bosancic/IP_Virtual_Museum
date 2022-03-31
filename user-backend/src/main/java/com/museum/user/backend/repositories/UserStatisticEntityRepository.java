package com.museum.user.backend.repositories;

import com.museum.user.backend.model.entities.UserStatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatisticEntityRepository extends JpaRepository<UserStatisticEntity, Integer> {
}
