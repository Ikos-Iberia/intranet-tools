package com.saniikos.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.dto.user.UserRequest;
import com.saniikos.backend.dto.user.UserResponse;
import com.saniikos.backend.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/username/{username}")
    public UserResponse findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }

    @GetMapping("/email/{email}")
    public UserResponse findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        return userService.save(request);
    }

    @PutMapping("/{id}")
    public UserResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

}