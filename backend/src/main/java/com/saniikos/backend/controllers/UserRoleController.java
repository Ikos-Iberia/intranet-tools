package com.saniikos.backend.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.dto.userrole.UserRoleRequest;
import com.saniikos.backend.dto.userrole.UserRoleResponse;
import com.saniikos.backend.services.UserRoleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(name = "User Roles", description = "User role management endpoints")
@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @Operation(summary = "Get all user roles")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserRoleResponse> findAll() {
        return userRoleService.findAll();
    }

    @Operation(summary = "Get user roles by user ID")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public List<UserRoleResponse> findByUser(
            @PathVariable Long userId) {

        return userRoleService.findByUser(userId);
    }

    @Operation(summary = "Get user roles by role ID")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/role/{roleId}")
    public List<UserRoleResponse> findByRole(
            @PathVariable Long roleId) {

        return userRoleService.findByRole(roleId);
    }

    @Operation(summary = "Create a new user role association")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public UserRoleResponse create(
            @Valid @RequestBody UserRoleRequest request) {

        return userRoleService.save(request);
    }

    @Operation(summary = "Delete a user role association")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}/{roleId}")
    public void delete(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        userRoleService.delete(userId, roleId);
    }

}