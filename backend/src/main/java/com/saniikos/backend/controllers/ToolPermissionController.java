package com.saniikos.backend.controllers;

import java.util.List;

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

    @GetMapping
    public List<ToolPermissionResponse> findAll() {
        return toolPermissionService.findAll();
    }

    @GetMapping("/tool/{toolId}")
    public List<ToolPermissionResponse> findByTool(@PathVariable Long toolId) {
        return toolPermissionService.findByTool(toolId);
    }

    @GetMapping("/role/{roleId}")
    public List<ToolPermissionResponse> findByRole(@PathVariable Long roleId) {
        return toolPermissionService.findByRole(roleId);
    }

    @PostMapping
    public ToolPermissionResponse create(
            @Valid @RequestBody ToolPermissionRequest request) {

        return toolPermissionService.save(request);
    }

    @DeleteMapping("/{toolId}/{roleId}")
    public void delete(
            @PathVariable Long toolId,
            @PathVariable Long roleId) {

        toolPermissionService.delete(toolId, roleId);
    }

}