package com.museum.user.backend.model;

import lombok.Data;

@Data
public class Tour {
    private Integer id;
    private String date;
    private double duration;
    private double price;
    private Integer museumId;
    private String museumName;
}
