package com.saniikos.backend.controllers;

import com.saniikos.backend.dto.toolvisibility.ToolVisibilityRequest;
import com.saniikos.backend.dto.toolvisibility.ToolVisibilityResponse;
import com.saniikos.backend.services.ToolVisibilityService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Tool Visibilities", description = "Tool visibility management endpoints")
@RestController
@RequestMapping("/api/tool-visibilities")
public class ToolVisibilityController {

    private final ToolVisibilityService toolVisibilityService;

    public ToolVisibilityController(ToolVisibilityService toolVisibilityService) {
        this.toolVisibilityService = toolVisibilityService;
    }

    @Operation(summary = "Get all tool visibilities")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<ToolVisibilityResponse> findAll() {
        return toolVisibilityService.findAll();
    }

    @Operation(summary = "Get tool visibility by tool ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ToolVisibilityResponse findById(@PathVariable Long id) {
        return toolVisibilityService.findById(id);
    }

    @Operation(summary = "Get tool visibility by code")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/code/{code}")
    public ToolVisibilityResponse findByCode(@PathVariable String code) {
        return toolVisibilityService.findByCode(code);
    }

    @Operation(summary = "Create a new tool visibility association")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ToolVisibilityResponse create(@Valid @RequestBody ToolVisibilityRequest request) {
        return toolVisibilityService.save(request);
    }

    @Operation(summary = "Update an existing tool visibility association")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ToolVisibilityResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ToolVisibilityRequest request) {

        return toolVisibilityService.update(id, request);
    }

    @Operation(summary = "Delete a tool visibility association")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        toolVisibilityService.delete(id);
    }

}