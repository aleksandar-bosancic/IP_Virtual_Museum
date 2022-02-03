package com.museum.user.backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "media")
public class MediaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "path")
    private String path;
    private Boolean isVideo;
    @ManyToOne
    @JoinColumn(name = "museum_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private MuseumEntity museum;

    @Basic
    @Column(name = "is_video")
    public Boolean getVideo() {
        return isVideo;
    }

    public void setVideo(Boolean video) {
        isVideo = video;
    }

}
