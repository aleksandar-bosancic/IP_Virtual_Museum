package com.museum.user.backend.repositories;

import com.museum.user.backend.model.entities.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaEntityRepository extends JpaRepository<MediaEntity, Integer> {

}
