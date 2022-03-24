package com.museum.user.backend.controllers;

import com.museum.user.backend.model.Media;
import com.museum.user.backend.repositories.MediaEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/media")
public class MediaController {
    private final MediaEntityRepository repository;
    private final ModelMapper modelMapper;

    public MediaController(MediaEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @GetMapping()
    List<Media> getMediaURLs(@RequestParam(value = "id", required = false) Integer id) {
        return repository.findAll().stream().filter(mediaEntity -> mediaEntity.getMuseum().getId().equals(id))
                .map(mediaEntity -> modelMapper.map(mediaEntity, Media.class)).toList();
    }
}
