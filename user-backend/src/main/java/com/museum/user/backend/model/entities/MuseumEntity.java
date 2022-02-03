package com.museum.user.backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "museum")
public class MuseumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "type")
    private String type;
    @Basic
    @Column(name = "latitude")
    private Float latitude;
    @Basic
    @Column(name = "longitude")
    private Float longitude;
    @OneToMany(mappedBy = "museum")
    private List<MediaEntity> mediaList;
    @OneToMany(mappedBy = "museum")
    @JsonIgnore
    private List<TourEntity> tourList;

}
