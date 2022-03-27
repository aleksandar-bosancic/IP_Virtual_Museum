package com.museum.user.backend.controllers;

import com.museum.user.backend.model.LogRequest;
import com.museum.user.backend.model.entities.LogsEntity;
import com.museum.user.backend.repositories.LogsEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogsController {
    private final LogsEntityRepository repository;
    private final ModelMapper modelMapper;

    public LogsController(LogsEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/log")
    void log(@RequestBody LogRequest logRequest){
        System.out.println(logRequest);
        LogsEntity logsEntity = modelMapper.map(logRequest, LogsEntity.class);
        repository.saveAndFlush(logsEntity);
    }
}
