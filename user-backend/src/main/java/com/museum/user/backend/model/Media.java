package com.museum.user.backend.model;

import lombok.Data;

@Data
public class Media {
    private Integer id;
    private Integer museumId;
    private String url;
    private boolean isVideo;
}
