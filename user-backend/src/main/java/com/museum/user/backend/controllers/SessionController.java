package com.museum.user.backend.controllers;

import com.museum.user.backend.repositories.SessionEntityRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    private final SessionEntityRepository repository;

    public SessionController(SessionEntityRepository repository) {
        this.repository = repository;
    }

    
}
