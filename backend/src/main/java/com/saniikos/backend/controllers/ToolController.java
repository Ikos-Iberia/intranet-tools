package com.saniikos.backend.controllers;

import com.saniikos.backend.dto.tool.ToolRequest;
import com.saniikos.backend.dto.tool.ToolResponse;
import com.saniikos.backend.services.ToolService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tools")
public class ToolController {

    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping
    public List<ToolResponse> findAll() {
        return toolService.findAll();
    }

    @GetMapping("/{id}")
    public ToolResponse findById(@PathVariable Long id) {
        return toolService.findById(id);
    }

    @GetMapping("/code/{code}")
    public ToolResponse findByCode(@PathVariable String code) {
        return toolService.findByCode(code);
    }

    @GetMapping("/route/{route}")
    public ToolResponse findByRoute(@PathVariable String route) {
        return toolService.findByRoute(route);
    }

    @GetMapping("/enabled")
    public List<ToolResponse> findEnabled() {
        return toolService.findEnabled();
    }

    @PostMapping
    public ToolResponse create(@Valid @RequestBody ToolRequest request) {
        return toolService.save(request);
    }

    @PutMapping("/{id}")
    public ToolResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ToolRequest request) {
        return toolService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        toolService.delete(id);
    }
}