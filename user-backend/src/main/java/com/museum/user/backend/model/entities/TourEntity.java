package com.museum.user.backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "tour")
public class TourEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @Basic
    @Column(name = "duration")
    private Double duration;
    @Basic
    @Column(name = "price")
    private Double price;
    @ManyToOne
    @JoinColumn(name = "museum_id", referencedColumnName = "id", nullable = false)
    private MuseumEntity museum;

}
