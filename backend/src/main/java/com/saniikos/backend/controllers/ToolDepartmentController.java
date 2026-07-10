package com.saniikos.backend.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.saniikos.backend.dto.tooldepartment.ToolDepartmentRequest;
import com.saniikos.backend.dto.tooldepartment.ToolDepartmentResponse;
import com.saniikos.backend.services.ToolDepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tool-departments")
public class ToolDepartmentController {

    private final ToolDepartmentService toolDepartmentService;

    public ToolDepartmentController(ToolDepartmentService toolDepartmentService) {
        this.toolDepartmentService = toolDepartmentService;
    }

    @GetMapping
    public List<ToolDepartmentResponse> findAll() {
        return toolDepartmentService.findAll();
    }

    @GetMapping("/tool/{toolId}")
    public List<ToolDepartmentResponse> findByTool(@PathVariable Long toolId) {
        return toolDepartmentService.findByTool(toolId);
    }

    @GetMapping("/department/{departmentId}")
    public List<ToolDepartmentResponse> findByDepartment(@PathVariable Long departmentId) {
        return toolDepartmentService.findByDepartment(departmentId);
    }

    @PostMapping
    public ToolDepartmentResponse create(
            @Valid @RequestBody ToolDepartmentRequest request) {

        return toolDepartmentService.save(request);
    }

    @DeleteMapping("/{toolId}/{departmentId}")
    public void delete(
            @PathVariable Long toolId,
            @PathVariable Long departmentId) {

        toolDepartmentService.delete(toolId, departmentId);
    }

}