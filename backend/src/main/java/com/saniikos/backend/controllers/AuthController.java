package com.saniikos.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.authorization.LoginRequestDTO;
import com.saniikos.backend.authorization.LoginResponseDTO;
import com.saniikos.backend.services.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;


    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {


        LoginResponseDTO response =
                authService.login(request);


        return ResponseEntity.ok(response);
    }

}