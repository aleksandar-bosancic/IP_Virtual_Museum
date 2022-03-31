package com.museum.user.backend.model;

import lombok.Data;

@Data
public class Session {
    private String username;
    private String key;
    private String validUntil;
}
