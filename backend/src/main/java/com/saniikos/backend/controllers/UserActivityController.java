package com.saniikos.backend.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.dto.useractivity.UserActivityRequest;
import com.saniikos.backend.dto.useractivity.UserActivityResponse;
import com.saniikos.backend.services.UserActivityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(name = "User Activities", description = "User activity management endpoints")
@RestController
@RequestMapping("/api/user-activities")
public class UserActivityController {

    private final UserActivityService userActivityService;

    public UserActivityController(
            UserActivityService userActivityService) {

        this.userActivityService = userActivityService;
    }

    @Operation(summary = "Get all user activities")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserActivityResponse> findAll() {

        return userActivityService.findAll();
    }

    @Operation(summary = "Get user activity by ID")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public UserActivityResponse findById(
            @PathVariable Long id) {

        return userActivityService.findById(id);
    }

    @Operation(summary = "Get user activities by user ID")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/user/{userId}")
    public List<UserActivityResponse> findByUser(
            @PathVariable Long userId) {

        return userActivityService.findByUser(userId);
    }

    @Operation(summary = "Get user activities by tool ID")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/tool/{toolId}")
    public List<UserActivityResponse> findByTool(
            @PathVariable Long toolId) {

        return userActivityService.findByTool(toolId);
    }

    @Operation(summary = "Get user activities by action code")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/action/{actionCode}")
    public List<UserActivityResponse> findByActionCode(
            @PathVariable String actionCode) {

        return userActivityService.findByActionCode(actionCode);
    }

    @Operation(summary = "Get user activities by success status")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/success/{success}")
    public List<UserActivityResponse> findBySuccess(
            @PathVariable Boolean success) {

        return userActivityService.findBySuccess(success);
    }

    @Operation(summary = "Get user activities by date range")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/date-range")
    public List<UserActivityResponse> findByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        return userActivityService.findByDateRange(start, end);
    }

    @Operation(summary = "Create a new user activity")
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public UserActivityResponse create(
            @Valid @RequestBody UserActivityRequest request) {

        return userActivityService.save(request);
    }

}