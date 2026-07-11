package com.saniikos.backend.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.dto.toolpermission.ToolPermissionRequest;
import com.saniikos.backend.dto.toolpermission.ToolPermissionResponse;
import com.saniikos.backend.services.ToolPermissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tool-permissions")
public class ToolPermissionController {

    private final ToolPermissionService toolPermissionService;

    public ToolPermissionController(ToolPermissionService toolPermissionService) {
        this.toolPermissionService = toolPermissionService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<ToolPermissionResponse> findAll() {
        return toolPermissionService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tool/{toolId}")
    public List<ToolPermissionResponse> findByTool(@PathVariable Long toolId) {
        return toolPermissionService.findByTool(toolId);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/role/{roleId}")
    public List<ToolPermissionResponse> findByRole(@PathVariable Long roleId) {
        return toolPermissionService.findByRole(roleId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ToolPermissionResponse create(
            @Valid @RequestBody ToolPermissionRequest request) {

        return toolPermissionService.save(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{toolId}/{roleId}")
    public void delete(
            @PathVariable Long toolId,
            @PathVariable Long roleId) {

        toolPermissionService.delete(toolId, roleId);
    }

}