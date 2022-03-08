package com.museum.user.backend.controllers;

import com.museum.user.backend.model.LoginRequest;
import com.museum.user.backend.model.LoginResponse;
import com.museum.user.backend.model.entities.UserEntity;
import com.museum.user.backend.repositories.UserEntityRepository;
import com.museum.user.backend.util.Util;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class UserController {
    private final UserEntityRepository repository;
    String dgst = "";


    public UserController(UserEntityRepository repository) {
        this.repository = repository;
    }

    List<UserEntity> findAll(){
        return repository.findAll();
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    LoginResponse login(@RequestBody LoginRequest loginRequest){
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

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    boolean testAuth(@RequestHeader HttpHeaders authorization){
        System.out.println(authorization.get(HttpHeaders.AUTHORIZATION));
        return true;
    }
}
