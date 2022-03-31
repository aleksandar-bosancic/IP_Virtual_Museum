package com.museum.user.backend.model.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "user_statistic")
public class UserStatisticEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "time_active", nullable = false)
    private Timestamp timeActive;

}
