package com.museum.user.backend.model;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String name;
    private String lastName;
    private String eMail;
}
