package com.museum.user.backend.controllers;

import com.museum.user.backend.model.entities.TourEntity;
import com.museum.user.backend.repositories.TourEntityRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {
    private final TourEntityRepository repository;

    public TourController(TourEntityRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    List<TourEntity> findAll(){
        return repository.findAll();
    }
}
