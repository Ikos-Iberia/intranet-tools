package com.saniikos.backend.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.dto.user.UserRequest;
import com.saniikos.backend.dto.user.UserResponse;
import com.saniikos.backend.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(name = "Users", description = "User management endpoints")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get all users")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @Operation(summary = "Get user by ID")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(summary = "Get user by username")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/username/{username}")
    public UserResponse findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @Operation(summary = "Get user by email")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/email/{email}")
    public UserResponse findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @Operation(summary = "Create a new user")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return userService.save(request);
    }

    @Operation(summary = "Update an existing user")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        return userService.update(id, request);
    }

    @Operation(summary = "Delete a user")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}