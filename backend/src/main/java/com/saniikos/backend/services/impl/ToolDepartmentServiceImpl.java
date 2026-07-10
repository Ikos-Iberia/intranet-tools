package com.saniikos.backend.services.impl;

import com.saniikos.backend.dto.tooldepartment.ToolDepartmentRequest;
import com.saniikos.backend.dto.tooldepartment.ToolDepartmentResponse;
import com.saniikos.backend.entities.Department;
import com.saniikos.backend.entities.Tool;
import com.saniikos.backend.entities.ToolDepartment;
import com.saniikos.backend.entities.ToolDepartmentId;
import com.saniikos.backend.mappers.ToolDepartmentMapper;
import com.saniikos.backend.repositories.DepartmentRepository;
import com.saniikos.backend.repositories.ToolDepartmentRepository;
import com.saniikos.backend.repositories.ToolRepository;
import com.saniikos.backend.services.ToolDepartmentService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ToolDepartmentServiceImpl implements ToolDepartmentService {

    private final ToolDepartmentRepository toolDepartmentRepository;
    private final ToolRepository toolRepository;
    private final DepartmentRepository departmentRepository;
    private final ToolDepartmentMapper toolDepartmentMapper;

    public ToolDepartmentServiceImpl(
            ToolDepartmentRepository toolDepartmentRepository,
            ToolRepository toolRepository,
            DepartmentRepository departmentRepository,
            ToolDepartmentMapper toolDepartmentMapper) {

        this.toolDepartmentRepository = toolDepartmentRepository;
        this.toolRepository = toolRepository;
        this.departmentRepository = departmentRepository;
        this.toolDepartmentMapper = toolDepartmentMapper;
    }

    @Override
    public List<ToolDepartmentResponse> findAll() {
        return toolDepartmentRepository.findAll()
                .stream()
                .map(toolDepartmentMapper::toResponse)
                .toList();
    }

    @Override
    public List<ToolDepartmentResponse> findByTool(Long toolId) {

        Tool tool = toolRepository.findById(toolId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool not found with id " + toolId));

        return toolDepartmentRepository.findByTool(tool)
                .stream()
                .map(toolDepartmentMapper::toResponse)
                .toList();
    }

    @Override
    public List<ToolDepartmentResponse> findByDepartment(Long departmentId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Department not found with id " + departmentId));

        return toolDepartmentRepository.findByDepartment(department)
                .stream()
                .map(toolDepartmentMapper::toResponse)
                .toList();
    }

    @Override
    public ToolDepartmentResponse save(ToolDepartmentRequest request) {

        Tool tool = toolRepository.findById(request.getToolId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Tool not found with id " + request.getToolId()));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Department not found with id " + request.getDepartmentId()));

        ToolDepartmentId id = new ToolDepartmentId(
                request.getToolId(),
                request.getDepartmentId());

        if (toolDepartmentRepository.existsById(id)) {
            throw new IllegalArgumentException("The relationship already exists.");
        }

        ToolDepartment toolDepartment = new ToolDepartment();

        toolDepartment.setId(id);
        toolDepartment.setTool(tool);
        toolDepartment.setDepartment(department);

        toolDepartment = toolDepartmentRepository.save(toolDepartment);

        return toolDepartmentMapper.toResponse(toolDepartment);
    }

    @Override
    public void delete(Long toolId, Long departmentId) {

        ToolDepartmentId id = new ToolDepartmentId(toolId, departmentId);

        ToolDepartment toolDepartment = toolDepartmentRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Relationship not found between tool " + toolId +
                                " and department " + departmentId));

        toolDepartmentRepository.delete(toolDepartment);
    }
}