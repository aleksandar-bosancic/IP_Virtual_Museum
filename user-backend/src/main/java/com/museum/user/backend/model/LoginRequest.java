package com.museum.user.backend.model;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
