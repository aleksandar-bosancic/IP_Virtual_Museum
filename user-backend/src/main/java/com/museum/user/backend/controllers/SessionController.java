package com.museum.user.backend.controllers;

import com.museum.user.backend.model.Session;
import com.museum.user.backend.model.SessionRequest;
import com.museum.user.backend.repositories.SessionEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SessionController {
    private final SessionEntityRepository repository;
    private final ModelMapper modelMapper;

    public SessionController(SessionEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/get-session")
    public Session getSessionByUsername(@RequestBody SessionRequest sessionRequest){
        return modelMapper.map(repository.findByUsername(sessionRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Session expired")), Session.class);
    }

    @Transactional
    @PostMapping("/invalidate-session")
    public void invalidateSession(@RequestBody SessionRequest sessionRequest){
        repository.deleteByUsername(sessionRequest.getUsername());
    }
}
