package com.saniikos.backend.services.impl;

import com.saniikos.backend.dto.role.RoleRequest;
import com.saniikos.backend.dto.role.RoleResponse;
import com.saniikos.backend.entities.Role;
import com.saniikos.backend.mappers.RoleMapper;
import com.saniikos.backend.repositories.RoleRepository;
import com.saniikos.backend.services.RoleService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(
            RoleRepository roleRepository,
            RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public List<RoleResponse> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(roleMapper::toResponse)
                .toList();
    }

    @Override
    public RoleResponse findById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Role not found with id " + id));

        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse findByCode(String code) {
        Role role = roleRepository.findByCode(code)
                .orElseThrow(() ->
                        new EntityNotFoundException("Role not found with code " + code));

        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse save(RoleRequest request) {

        if (roleRepository.findByCode(request.getCode()).isPresent()) {
            throw new IllegalArgumentException("Role code already exists.");
        }

        Role role = roleMapper.toEntity(request);

        role = roleRepository.save(role);

        return roleMapper.toResponse(role);
    }

    @Override
    public RoleResponse update(Long id, RoleRequest request) {

        Role existing = roleRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Role not found with id " + id));

        roleRepository.findByCode(request.getCode())
                .filter(role -> !role.getId().equals(id))
                .ifPresent(role -> {
                    throw new IllegalArgumentException("Role code already exists.");
                });

        roleMapper.updateEntity(request, existing);

        existing = roleRepository.save(existing);

        return roleMapper.toResponse(existing);
    }

    @Override
    public void delete(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Role not found with id " + id));

        if (Boolean.TRUE.equals(role.getSystem())) {
            throw new IllegalArgumentException("System roles cannot be deleted.");
        }

        // Soft delete
        role.setActive(false);

        roleRepository.save(role);
    }
}