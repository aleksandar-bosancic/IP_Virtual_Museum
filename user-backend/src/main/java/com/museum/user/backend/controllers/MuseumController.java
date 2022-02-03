package com.museum.user.backend.controllers;

import com.museum.user.backend.model.entities.MuseumEntity;
import com.museum.user.backend.repositories.MuseumEntityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/museums")
public class MuseumController {
    private final MuseumEntityRepository repository;


    public MuseumController(MuseumEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<MuseumEntity> findAll(){
        return repository.findAll();
    }
}
