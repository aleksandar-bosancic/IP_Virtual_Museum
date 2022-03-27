package com.museum.user.backend.model.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "session")
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic
    @Column(name = "key", nullable = false, length = 45)
    private String key;
    @Basic
    @Column(name = "valid_until", nullable = false)
    private Timestamp validUntil;

}
