package com.museum.user.backend.repositories;

import com.museum.user.backend.model.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository <UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
