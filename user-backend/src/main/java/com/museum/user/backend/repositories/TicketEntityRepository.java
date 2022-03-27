package com.museum.user.backend.repositories;

import com.museum.user.backend.model.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketEntityRepository extends JpaRepository<TicketEntity, String> {

}
