package com.saniikos.backend.controllers;

import com.saniikos.backend.dto.toolcategory.ToolCategoryRequest;
import com.saniikos.backend.dto.toolcategory.ToolCategoryResponse;
import com.saniikos.backend.services.ToolCategoryService;
import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;


@Tag(name = "Tool Categories", description = "Tool category management endpoints")
@RestController
@RequestMapping("/api/tool-categories")
public class ToolCategoryController {

    private final ToolCategoryService toolCategoryService;

    public ToolCategoryController(ToolCategoryService toolCategoryService) {
        this.toolCategoryService = toolCategoryService;
    }

    @Operation(summary = "Get all tool categories")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<ToolCategoryResponse> findAll() {
        return toolCategoryService.findAll();
    }

    @Operation(summary = "Get tool category by ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ToolCategoryResponse findById(@PathVariable Long id) {
        return toolCategoryService.findById(id);
    }

    @Operation(summary = "Get tool category by code")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/code/{code}")
    public ToolCategoryResponse findByCode(@PathVariable String code) {
        return toolCategoryService.findByCode(code);
    }

    @Operation(summary = "Create a new tool category")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ToolCategoryResponse create(@Valid @RequestBody ToolCategoryRequest request) {
        return toolCategoryService.save(request);
    }

    @Operation(summary = "Update an existing tool category")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ToolCategoryResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ToolCategoryRequest request) {
        return toolCategoryService.update(id, request);
    }

    @Operation(summary = "Delete a tool category by ID")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        toolCategoryService.delete(id);
    }
}