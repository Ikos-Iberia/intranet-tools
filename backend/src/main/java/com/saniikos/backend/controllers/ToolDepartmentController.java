package com.saniikos.backend.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.dto.tooldepartment.ToolDepartmentRequest;
import com.saniikos.backend.dto.tooldepartment.ToolDepartmentResponse;
import com.saniikos.backend.services.ToolDepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@Tag(name = "Tool Departments", description = "Tool department management endpoints")
@RestController
@RequestMapping("/api/tool-departments")
public class ToolDepartmentController {

    private final ToolDepartmentService toolDepartmentService;

    public ToolDepartmentController(ToolDepartmentService toolDepartmentService) {
        this.toolDepartmentService = toolDepartmentService;
    }

    @Operation(summary = "Get all tool departments")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<ToolDepartmentResponse> findAll() {
        return toolDepartmentService.findAll();
    }

    @Operation(summary = "Get tool departments by tool ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/tool/{toolId}")
    public List<ToolDepartmentResponse> findByTool(@PathVariable Long toolId) {
        return toolDepartmentService.findByTool(toolId);
    }

    @Operation(summary = "Get tool departments by department ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/department/{departmentId}")
    public List<ToolDepartmentResponse> findByDepartment(@PathVariable Long departmentId) {
        return toolDepartmentService.findByDepartment(departmentId);
    }

    @Operation(summary = "Create a new tool department association")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ToolDepartmentResponse create(
            @Valid @RequestBody ToolDepartmentRequest request) {

        return toolDepartmentService.save(request);
    }

    @Operation(summary = "Delete a tool department association")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{toolId}/{departmentId}")
    public void delete(
            @PathVariable Long toolId,
            @PathVariable Long departmentId) {

        toolDepartmentService.delete(toolId, departmentId);
    }

}