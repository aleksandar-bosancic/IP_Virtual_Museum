package com.museum.user.backend.controllers;

import com.museum.user.backend.model.LoginRequest;
import com.museum.user.backend.model.LoginResponse;
import com.museum.user.backend.model.RegisterRequest;
import com.museum.user.backend.model.User;
import com.museum.user.backend.model.entities.UserEntity;
import com.museum.user.backend.repositories.UserEntityRepository;
import com.museum.user.backend.util.Util;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class UserController {
    private final UserEntityRepository repository;
    private final ModelMapper modelMapper;
    @PersistenceContext
    private EntityManager entityManager;
    String dgst = "";


    public UserController(UserEntityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    List<UserEntity> findAll() {
        return repository.findAll();
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    LoginResponse login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        String hexHash = null;
        String credentials = loginRequest.getUsername() + loginRequest.getPassword();
        try {
            hexHash = Util.bytesToHex(MessageDigest.getInstance("SHA3-256").digest(credentials.getBytes(StandardCharsets.UTF_8)));
            dgst = hexHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (this.findAll().stream().anyMatch(userEntity -> userEntity.getUsername().equals(loginRequest.getUsername())
                && userEntity.getPassword().equals(loginRequest.getPassword()))) {
            return new LoginResponse(hexHash);
        } else {
            return null;
        }
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public User register(@RequestBody RegisterRequest registerRequest) throws ResponseStatusException {
        if (registerRequest.getUsername().equals("") || registerRequest.getPassword().equals("")
                || registerRequest.getName().equals("") || registerRequest.getLastName().equals("")
                || registerRequest.getEMail().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        UserEntity userEntity = modelMapper.map(registerRequest, UserEntity.class);
        userEntity.setId(null);
        userEntity.setApproved(false);
        userEntity = repository.saveAndFlush(userEntity);
        entityManager.refresh(userEntity);
        return modelMapper.map(userEntity, User.class);
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    boolean testAuth(@RequestHeader HttpHeaders authorization) {
        System.out.println(authorization.get(HttpHeaders.AUTHORIZATION));
        return true;
    }
}
