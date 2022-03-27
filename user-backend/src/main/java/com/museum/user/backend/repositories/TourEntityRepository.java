package com.museum.user.backend.repositories;

import com.museum.user.backend.model.entities.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourEntityRepository extends JpaRepository<TourEntity, Integer> {
    @Query("SELECT distinct t from TourEntity t inner join TicketEntity ti on t.id=ti.tour.id where ti.user.id=?1")
    List<TourEntity> findAllByUserId(int userId);
}
