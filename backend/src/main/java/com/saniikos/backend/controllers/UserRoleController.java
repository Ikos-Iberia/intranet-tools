package com.saniikos.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.dto.userrole.UserRoleRequest;
import com.saniikos.backend.dto.userrole.UserRoleResponse;
import com.saniikos.backend.services.UserRoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-roles")
public class UserRoleController {

    private final UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public List<UserRoleResponse> findAll() {
        return userRoleService.findAll();
    }


    @GetMapping("/user/{userId}")
    public List<UserRoleResponse> findByUser(
            @PathVariable Long userId) {

        return userRoleService.findByUser(userId);
    }


    @GetMapping("/role/{roleId}")
    public List<UserRoleResponse> findByRole(
            @PathVariable Long roleId) {

        return userRoleService.findByRole(roleId);
    }


    @PostMapping
    public UserRoleResponse create(
            @Valid @RequestBody UserRoleRequest request) {

        return userRoleService.save(request);
    }


    @DeleteMapping("/{userId}/{roleId}")
    public void delete(
            @PathVariable Long userId,
            @PathVariable Long roleId) {

        userRoleService.delete(userId, roleId);
    }

}