package com.museum.user.backend.controllers;

import com.museum.user.backend.model.entities.MuseumEntity;
import com.museum.user.backend.repositories.MuseumEntityRepository;
import com.museum.user.backend.services.AuthorizationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class MuseumController {
    private final MuseumEntityRepository repository;
    private final AuthorizationService authorizationService;

    public MuseumController(MuseumEntityRepository repository, AuthorizationService authorizationService) {
        this.repository = repository;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/museums")
    List<MuseumEntity> findAll(@RequestHeader String authorization){
        if (authorizationService.validateKey(authorization)) {
            return repository.findAll();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
