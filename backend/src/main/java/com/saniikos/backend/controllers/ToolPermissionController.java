package com.saniikos.backend.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.dto.toolpermission.ToolPermissionRequest;
import com.saniikos.backend.dto.toolpermission.ToolPermissionResponse;
import com.saniikos.backend.services.ToolPermissionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;


@Tag(name = "Tool Permissions", description = "Tool permission management endpoints")
@RestController
@RequestMapping("/api/tool-permissions")
public class ToolPermissionController {

    private final ToolPermissionService toolPermissionService;

    public ToolPermissionController(ToolPermissionService toolPermissionService) {
        this.toolPermissionService = toolPermissionService;
    }

    @Operation(summary = "Get all tool permissions")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<ToolPermissionResponse> findAll() {
        return toolPermissionService.findAll();
    }

    @Operation(summary = "Get tool permission by tool ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tool/{toolId}")
    public List<ToolPermissionResponse> findByTool(@PathVariable Long toolId) {
        return toolPermissionService.findByTool(toolId);
    }

    @Operation(summary = "Get tool permission by role ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/role/{roleId}")
    public List<ToolPermissionResponse> findByRole(@PathVariable Long roleId) {
        return toolPermissionService.findByRole(roleId);
    }

    @Operation(summary = "Create a new tool permission association")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ToolPermissionResponse create(
            @Valid @RequestBody ToolPermissionRequest request) {

        return toolPermissionService.save(request);
    }

    @Operation(summary = "Delete a tool permission association")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{toolId}/{roleId}")
    public void delete(
            @PathVariable Long toolId,
            @PathVariable Long roleId) {

        toolPermissionService.delete(toolId, roleId);
    }

}