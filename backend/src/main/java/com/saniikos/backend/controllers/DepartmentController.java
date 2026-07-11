package com.saniikos.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.saniikos.backend.services.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.saniikos.backend.dto.department.DepartmentResponse;
import com.saniikos.backend.dto.department.DepartmentRequest;

import java.util.List;

import jakarta.validation.Valid;

@Tag(name = "Departments", description = "Department management and organization endpoints")
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Operation(summary = "Get all departments")
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<DepartmentResponse> getAll() {
        return departmentService.findAll();
    }

    @Operation(summary = "Get a department by its ID")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public DepartmentResponse getById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @Operation(summary = "Get a department by its code")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/code/{code}")
    public DepartmentResponse getByCode(@PathVariable String code) {
        return departmentService.findByCode(code);
    }

    @Operation(summary = "Create a new department")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DepartmentResponse> create(
            @Valid @RequestBody DepartmentRequest request) {

        DepartmentResponse response = departmentService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Update an existing department")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public DepartmentResponse update(
            @PathVariable Long id,
            @Valid @RequestBody DepartmentRequest request) {

        return departmentService.update(id, request);
    }

    @Operation(summary = "Delete a department")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        departmentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}