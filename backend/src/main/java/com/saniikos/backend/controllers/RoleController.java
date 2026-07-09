package com.saniikos.backend.controllers;

import com.saniikos.backend.dto.role.RoleRequest;
import com.saniikos.backend.dto.role.RoleResponse;
import com.saniikos.backend.services.RoleService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> findAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<RoleResponse> findByCode(@PathVariable String code) {
        return ResponseEntity.ok(roleService.findByCode(code));
    }

    @PostMapping
    public ResponseEntity<RoleResponse> save(@Valid @RequestBody RoleRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(roleService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody RoleRequest request) {

        return ResponseEntity.ok(roleService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        roleService.delete(id);

        return ResponseEntity.noContent().build();
    }
}