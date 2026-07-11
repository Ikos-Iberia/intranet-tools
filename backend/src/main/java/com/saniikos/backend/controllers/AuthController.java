package com.saniikos.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.authorization.LoginRequestDTO;
import com.saniikos.backend.authorization.LoginResponseDTO;
import com.saniikos.backend.authorization.UserInfo;
import com.saniikos.backend.authorization.security.JwtUtils;
import com.saniikos.backend.services.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(name = "Authentication", description = "User authentication and session management")
@RestController
@RequestMapping("/users")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService) {

        this.authService = authService;
    }

    @Operation(summary = "Authenticate user and return token")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequestDTO request) {

        LoginResponseDTO response = authService.login(request);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get current authenticated user information")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UserInfo> me() {

        UserInfo user = JwtUtils.getAuthenticatedUser();

        return ResponseEntity.ok(user);
    }

}