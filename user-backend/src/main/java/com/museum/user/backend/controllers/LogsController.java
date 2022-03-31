package com.museum.user.backend.controllers;

import com.museum.user.backend.model.LogRequest;
import com.museum.user.backend.model.entities.LogsEntity;
import com.museum.user.backend.repositories.LogsEntityRepository;
import com.museum.user.backend.services.AuthorizationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LogsController {
    private final LogsEntityRepository repository;
    private final ModelMapper modelMapper;
    private final AuthorizationService authorizationService;

    public LogsController(LogsEntityRepository repository, ModelMapper modelMapper, AuthorizationService authorizationService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.authorizationService = authorizationService;
    }

    @PostMapping("/log")
    void log(@RequestHeader("Authorization") String authorization, @RequestBody LogRequest logRequest){
        if (authorizationService.validateKey(authorization)) {
            LogsEntity logsEntity = modelMapper.map(logRequest, LogsEntity.class);
            repository.saveAndFlush(logsEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
