package com.museum.user.backend.services;

import com.museum.user.backend.model.Session;
import com.museum.user.backend.repositories.SessionEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationService {
    private final SessionEntityRepository sessionRepository;
    private final ModelMapper modelMapper;
    private List<Session> sessionList;

    public AuthorizationService(SessionEntityRepository sessionRepository, ModelMapper modelMapper) {
        this.sessionRepository = sessionRepository;
        this.modelMapper = modelMapper;
        this.sessionList = sessionRepository.findAll().stream()
                .map(sessionEntity -> modelMapper.map(sessionEntity, Session.class)).toList();
    }

    public void updateSessionList() {
        this.sessionList = sessionRepository.findAll().stream()
                .map(sessionEntity -> modelMapper.map(sessionEntity, Session.class)).toList();
    }

    public boolean validateKey(String key) {
        String[] keyParts = key.split(" ");
        if(keyParts.length < 2){
            return false;
        }
        return sessionList.stream().anyMatch(session -> session.getKey().equals(keyParts[1]));
    }
}
