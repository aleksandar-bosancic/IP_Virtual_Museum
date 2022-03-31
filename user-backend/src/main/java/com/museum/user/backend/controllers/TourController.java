package com.museum.user.backend.controllers;

import com.museum.user.backend.model.Tour;
import com.museum.user.backend.model.User;
import com.museum.user.backend.repositories.TourEntityRepository;
import com.museum.user.backend.repositories.UserEntityRepository;
import com.museum.user.backend.services.AuthorizationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TourController {
    private final TourEntityRepository repository;
    private final UserEntityRepository userRepository;
    private final ModelMapper modelMapper;
    private final AuthorizationService authorizationService;


    public TourController(TourEntityRepository repository, UserEntityRepository userRepository,
                          ModelMapper modelMapper, AuthorizationService authorizationService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authorizationService = authorizationService;
    }

    @GetMapping("/tours")
    List<Tour> findAll(@RequestHeader String authorization) {
        if (!authorizationService.validateKey(authorization)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return repository.findAll().stream().map(tourEntity -> modelMapper.map(tourEntity, Tour.class)).toList();
    }

    @GetMapping("/{id}/tours")
    List<Tour> findByMuseumId(@RequestHeader String authorization, @PathVariable Integer id) {
        if (!authorizationService.validateKey(authorization)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return this.findAll(authorization).stream().filter(tour -> tour.getMuseumId().equals(id)).toList();
    }

    @GetMapping("/tours/{username}")
    List<Tour> getPurchasedTours(@RequestHeader String authorization, @PathVariable String username) {
        if (!authorizationService.validateKey(authorization)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        User user = modelMapper.map(userRepository.findByUsername(username), User.class);
        return repository.findAllByUserId(user.getId()).stream().map(tourEntity -> modelMapper.map(tourEntity, Tour.class)).toList();
    }
}
