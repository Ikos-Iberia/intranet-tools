package com.saniikos.backend.controllers;

import com.saniikos.backend.dto.tool.ToolRequest;
import com.saniikos.backend.dto.tool.ToolResponse;
import com.saniikos.backend.services.ToolService;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Tools", description = "Tool management endpoints")
@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @Operation(summary = "Get all tools")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<ToolResponse> findAll() {
        return toolService.findAll();
    }

    @Operation(summary = "Get tool by ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ToolResponse findById(@PathVariable Long id) {
        return toolService.findById(id);
    }

    @Operation(summary = "Get tool by code")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/code/{code}")
    public ToolResponse findByCode(@PathVariable String code) {
        return toolService.findByCode(code);
    }

    @Operation(summary = "Get tool by route")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/route/{route}")
    public ToolResponse findByRoute(@PathVariable String route) {
        return toolService.findByRoute(route);
    }

    @Operation(summary = "Get all enabled tools")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/enabled")
    public List<ToolResponse> findEnabled() {
        return toolService.findEnabled();
    }

    @Operation(summary = "Create a new tool")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ToolResponse create(@Valid @RequestBody ToolRequest request) {
        return toolService.save(request);
    }

    @Operation(summary = "Update an existing tool")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ToolResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ToolRequest request) {
        return toolService.update(id, request);
    }

    @Operation(summary = "Delete a tool by ID")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        toolService.delete(id);
    }
}