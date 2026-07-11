package com.saniikos.backend.controllers;

import com.saniikos.backend.dto.toolcategory.ToolCategoryRequest;
import com.saniikos.backend.dto.toolcategory.ToolCategoryResponse;
import com.saniikos.backend.services.ToolCategoryService;
import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tool-categories")
public class ToolCategoryController {

    private final ToolCategoryService toolCategoryService;

    public ToolCategoryController(ToolCategoryService toolCategoryService) {
        this.toolCategoryService = toolCategoryService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<ToolCategoryResponse> findAll() {
        return toolCategoryService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ToolCategoryResponse findById(@PathVariable Long id) {
        return toolCategoryService.findById(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/code/{code}")
    public ToolCategoryResponse findByCode(@PathVariable String code) {
        return toolCategoryService.findByCode(code);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ToolCategoryResponse create(@Valid @RequestBody ToolCategoryRequest request) {
        return toolCategoryService.save(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ToolCategoryResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ToolCategoryRequest request) {
        return toolCategoryService.update(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        toolCategoryService.delete(id);
    }
}