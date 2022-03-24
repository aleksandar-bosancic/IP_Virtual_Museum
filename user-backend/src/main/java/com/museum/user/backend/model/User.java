package com.museum.user.backend.model;

import lombok.Data;

@Data
public class User {
    Integer id;
    String username;
    String password;
    String name;
    String lastName;
    String email;
    boolean isApproved;
}
