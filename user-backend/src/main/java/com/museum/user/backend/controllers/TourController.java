package com.museum.user.backend.controllers;

import com.museum.user.backend.model.Tour;
import com.museum.user.backend.repositories.TourEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TourController {
    private final TourEntityRepository repository;
    private final ModelMapper modelMapper;

    public TourController(TourEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    //TODO: napraviti da user moze kupiti tura i da korisnik moze pristupiti samo kupljenim turama

    @GetMapping("/tours")
    List<Tour> findAll(){
        return  repository.findAll().stream().map(tourEntity -> modelMapper.map(tourEntity, Tour.class)).toList();
    }

    @GetMapping("/{id}/tours")
    List<Tour> findByMuseumId(@PathVariable Integer id) {
        return this.findAll().stream().filter(tour -> tour.getMuseumId().equals(id)).toList();
    }
}
