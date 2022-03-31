package com.museum.user.backend.controllers;

import com.museum.user.backend.model.Media;
import com.museum.user.backend.model.Tour;
import com.museum.user.backend.model.entities.LogsEntity;
import com.museum.user.backend.repositories.MediaEntityRepository;
import com.museum.user.backend.repositories.TourEntityRepository;
import com.museum.user.backend.services.AuthorizationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@RestController
public class MediaController {
    private final MediaEntityRepository repository;
    private final TourEntityRepository tourRepository;
    private final ModelMapper modelMapper;
    private final AuthorizationService authorizationService;

    public MediaController(MediaEntityRepository repository, TourEntityRepository tourRepository, ModelMapper modelMapper, AuthorizationService authorizationService) {
        this.repository = repository;
        this.tourRepository = tourRepository;
        this.modelMapper = modelMapper;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/media")
    List<Media> getMediaURLs(@RequestHeader String authorization, @RequestParam(value = "id", required = false) Integer id) {
        if (!authorizationService.validateKey(authorization)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if (id == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Tour tour = modelMapper.map(tourRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST)), Tour.class);
        Instant currentTime = Instant.now();
        Instant tourTime = Timestamp.valueOf(tour.getDate()).toInstant();
        Instant tourEndTime = tourTime.plus(Duration.ofHours((long) tour.getDuration()));
        if (currentTime.compareTo(tourTime) >= 0 && currentTime.compareTo(tourEndTime) <= 0) {
            return repository.findAll().stream().filter(mediaEntity -> mediaEntity.getMuseum().getId().equals(tour.getMuseumId()))
                    .map(mediaEntity -> modelMapper.map(mediaEntity, Media.class)).toList();
        } else {
            return Collections.emptyList();
        }
    }
}
