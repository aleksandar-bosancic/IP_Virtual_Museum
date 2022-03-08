package com.museum.user.backend.model;

import lombok.Data;

@Data
public class LoginResponse {
    String key;

    public LoginResponse(String key) {
        this.key = key;
    }
}
