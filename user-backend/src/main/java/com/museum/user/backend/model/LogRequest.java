package com.museum.user.backend.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LogRequest {
    private String username;
    private String category;
    private String label;
    private Timestamp timestamp;
}
