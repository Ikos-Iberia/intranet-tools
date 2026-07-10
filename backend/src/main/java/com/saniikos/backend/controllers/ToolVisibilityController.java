package com.saniikos.backend.controllers;

import com.saniikos.backend.dto.toolvisibility.ToolVisibilityRequest;
import com.saniikos.backend.dto.toolvisibility.ToolVisibilityResponse;
import com.saniikos.backend.services.ToolVisibilityService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tool-visibilities")
public class ToolVisibilityController {

    private final ToolVisibilityService toolVisibilityService;

    public ToolVisibilityController(ToolVisibilityService toolVisibilityService) {
        this.toolVisibilityService = toolVisibilityService;
    }

    @GetMapping
    public List<ToolVisibilityResponse> findAll() {
        return toolVisibilityService.findAll();
    }

    @GetMapping("/{id}")
    public ToolVisibilityResponse findById(@PathVariable Long id) {
        return toolVisibilityService.findById(id);
    }

    @GetMapping("/code/{code}")
    public ToolVisibilityResponse findByCode(@PathVariable String code) {
        return toolVisibilityService.findByCode(code);
    }

    @PostMapping
    public ToolVisibilityResponse create(@Valid @RequestBody ToolVisibilityRequest request) {
        return toolVisibilityService.save(request);
    }

    @PutMapping("/{id}")
    public ToolVisibilityResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ToolVisibilityRequest request) {

        return toolVisibilityService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        toolVisibilityService.delete(id);
    }

}