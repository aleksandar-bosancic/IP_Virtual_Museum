package com.museum.user.backend.model;

import lombok.Data;

@Data
public class Ticket {
    private String id;
    private String userUsername;
    private String tourMuseumName;
    private String tourDate;
    private Double tourDuration;
    private Double tourPrice;
}
