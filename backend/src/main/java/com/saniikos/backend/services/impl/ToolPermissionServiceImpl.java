package com.saniikos.backend.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saniikos.backend.dto.toolpermission.ToolPermissionRequest;
import com.saniikos.backend.dto.toolpermission.ToolPermissionResponse;
import com.saniikos.backend.entities.Role;
import com.saniikos.backend.entities.Tool;
import com.saniikos.backend.entities.ToolPermission;
import com.saniikos.backend.entities.ToolPermissionId;
import com.saniikos.backend.mappers.ToolPermissionMapper;
import com.saniikos.backend.repositories.RoleRepository;
import com.saniikos.backend.repositories.ToolPermissionRepository;
import com.saniikos.backend.repositories.ToolRepository;
import com.saniikos.backend.services.ToolPermissionService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ToolPermissionServiceImpl implements ToolPermissionService {

    private final ToolPermissionRepository toolPermissionRepository;
    private final ToolRepository toolRepository;
    private final RoleRepository roleRepository;
    private final ToolPermissionMapper toolPermissionMapper;

    public ToolPermissionServiceImpl(
            ToolPermissionRepository toolPermissionRepository,
            ToolRepository toolRepository,
            RoleRepository roleRepository,
            ToolPermissionMapper toolPermissionMapper) {

        this.toolPermissionRepository = toolPermissionRepository;
        this.toolRepository = toolRepository;
        this.roleRepository = roleRepository;
        this.toolPermissionMapper = toolPermissionMapper;
    }

    @Override
    public List<ToolPermissionResponse> findAll() {
        return toolPermissionRepository.findAll()
                .stream()
                .map(toolPermissionMapper::toResponse)
                .toList();
    }

    @Override
    public List<ToolPermissionResponse> findByTool(Long toolId) {

        Tool tool = toolRepository.findById(toolId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool not found with id " + toolId));

        return toolPermissionRepository.findByTool(tool)
                .stream()
                .map(toolPermissionMapper::toResponse)
                .toList();
    }

    @Override
    public List<ToolPermissionResponse> findByRole(Long roleId) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Role not found with id " + roleId));

        return toolPermissionRepository.findByRole(role)
                .stream()
                .map(toolPermissionMapper::toResponse)
                .toList();
    }

    @Override
    public ToolPermissionResponse save(ToolPermissionRequest request) {

        Tool tool = toolRepository.findById(request.getToolId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool not found with id " + request.getToolId()));

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Role not found with id " + request.getRoleId()));

        ToolPermissionId id = new ToolPermissionId(
                request.getToolId(),
                request.getRoleId());

        if (toolPermissionRepository.existsById(id)) {
            throw new IllegalArgumentException("The relationship already exists.");
        }

        ToolPermission toolPermission = new ToolPermission();

        toolPermission.setId(id);
        toolPermission.setTool(tool);
        toolPermission.setRole(role);

        toolPermission = toolPermissionRepository.save(toolPermission);

        return toolPermissionMapper.toResponse(toolPermission);
    }

    @Override
    public void delete(Long toolId, Long roleId) {

        ToolPermissionId id = new ToolPermissionId(toolId, roleId);

        ToolPermission toolPermission = toolPermissionRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Relationship not found between tool "
                                        + toolId + " and role " + roleId));

        toolPermissionRepository.delete(toolPermission);
    }

}