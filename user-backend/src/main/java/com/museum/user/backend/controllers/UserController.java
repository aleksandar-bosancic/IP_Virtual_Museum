package com.museum.user.backend.controllers;

import com.museum.user.backend.model.*;
import com.museum.user.backend.model.entities.SessionEntity;
import com.museum.user.backend.model.entities.UserEntity;
import com.museum.user.backend.model.entities.UserStatisticEntity;
import com.museum.user.backend.repositories.SessionEntityRepository;
import com.museum.user.backend.repositories.UserEntityRepository;
import com.museum.user.backend.repositories.UserStatisticEntityRepository;
import com.museum.user.backend.services.AuthorizationService;
import com.museum.user.backend.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserEntityRepository repository;
    private final SessionEntityRepository sessionRepository;
    private final UserStatisticEntityRepository statisticRepository;
    private final ModelMapper modelMapper;
    private final AuthorizationService authorizationService;
    @PersistenceContext
    private EntityManager entityManager;
    String digest = "";


    public UserController(UserEntityRepository repository, SessionEntityRepository sessionRepository,
                          UserStatisticEntityRepository statisticRepository, ModelMapper modelMapper,
                          AuthorizationService authorizationService) {
        this.repository = repository;
        this.sessionRepository = sessionRepository;
        this.statisticRepository = statisticRepository;
        this.modelMapper = modelMapper;
        this.authorizationService = authorizationService;
    }

    List<UserEntity> findAll() {
        return repository.findAll();
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    LoginResponse login(@RequestBody LoginRequest loginRequest) {
        UserEntity user = this.findAll().stream().filter(userEntity -> userEntity.getUsername().equals(loginRequest.getUsername())
                && userEntity.getPassword().equals(loginRequest.getPassword())).findFirst().orElse(null);
        Optional<SessionEntity> session = sessionRepository.findByUsername(loginRequest.getUsername());
        if (session.isPresent()) {
            return new LoginResponse(session.get().getKey());
        }
        String key = null;
        String keyString = loginRequest.getUsername() + loginRequest.getPassword() + Instant.now();
        try {
            key = Util.bytesToHex(MessageDigest.getInstance("SHA3-256").digest(keyString.getBytes(StandardCharsets.UTF_8)));
            digest = key;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (user != null && user.getIsApproved()) {
            SessionEntity sessionEntity = new SessionEntity();
            UserStatisticEntity userStatistic = new UserStatisticEntity();
            userStatistic.setId(null);
            userStatistic.setTimeActive(Timestamp.from(Instant.now()));
            sessionEntity.setId(null);
            sessionEntity.setUsername(user.getUsername());
            sessionEntity.setKey(key);
            sessionEntity.setValidUntil(Timestamp.from(Instant.now().plus(Duration.ofHours(1))));
            statisticRepository.saveAndFlush(userStatistic);
            sessionRepository.saveAndFlush(sessionEntity);
            authorizationService.updateSessionList();
            return new LoginResponse(key);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public User register(@RequestBody RegisterRequest registerRequest) throws ResponseStatusException {
        if (registerRequest.getUsername().equals("") || registerRequest.getPassword().equals("")
                || registerRequest.getName().equals("") || registerRequest.getLastName().equals("")
                || registerRequest.getEmail().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = modelMapper.map(registerRequest, UserEntity.class);
        userEntity.setId(null);
        userEntity.setApproved(false);
        userEntity = repository.saveAndFlush(userEntity);
        entityManager.refresh(userEntity);
        return modelMapper.map(userEntity, User.class);
    }

    @PostMapping("logout")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void logout(@RequestBody SessionRequest sessionRequest) {
        this.sessionRepository.deleteByUsername(sessionRequest.getUsername());
        authorizationService.updateSessionList();
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    boolean testAuth(@RequestHeader("Authorization") String authorization) {
        return authorizationService.validateKey(authorization);
    }
}
