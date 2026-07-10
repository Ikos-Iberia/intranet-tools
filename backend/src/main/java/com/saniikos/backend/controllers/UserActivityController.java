package com.saniikos.backend.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.dto.useractivity.UserActivityRequest;
import com.saniikos.backend.dto.useractivity.UserActivityResponse;
import com.saniikos.backend.services.UserActivityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user-activities")
public class UserActivityController {

    private final UserActivityService userActivityService;

    public UserActivityController(
            UserActivityService userActivityService) {

        this.userActivityService = userActivityService;
    }


    @GetMapping
    public List<UserActivityResponse> findAll() {

        return userActivityService.findAll();
    }


    @GetMapping("/{id}")
    public UserActivityResponse findById(
            @PathVariable Long id) {

        return userActivityService.findById(id);
    }


    @GetMapping("/user/{userId}")
    public List<UserActivityResponse> findByUser(
            @PathVariable Long userId) {

        return userActivityService.findByUser(userId);
    }


    @GetMapping("/tool/{toolId}")
    public List<UserActivityResponse> findByTool(
            @PathVariable Long toolId) {

        return userActivityService.findByTool(toolId);
    }


    @GetMapping("/action/{actionCode}")
    public List<UserActivityResponse> findByActionCode(
            @PathVariable String actionCode) {

        return userActivityService.findByActionCode(actionCode);
    }


    @GetMapping("/success/{success}")
    public List<UserActivityResponse> findBySuccess(
            @PathVariable Boolean success) {

        return userActivityService.findBySuccess(success);
    }


    @GetMapping("/date-range")
    public List<UserActivityResponse> findByDateRange(
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime start,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime end) {

        return userActivityService.findByDateRange(start, end);
    }


    @PostMapping
    public UserActivityResponse create(
            @Valid @RequestBody UserActivityRequest request) {

        return userActivityService.save(request);
    }

}