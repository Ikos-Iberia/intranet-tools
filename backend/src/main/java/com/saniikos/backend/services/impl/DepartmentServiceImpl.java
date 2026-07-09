package com.saniikos.backend.services.impl;

import com.saniikos.backend.dto.department.DepartmentRequest;
import com.saniikos.backend.dto.department.DepartmentResponse;
import com.saniikos.backend.entities.Department;
import com.saniikos.backend.mappers.DepartmentMapper;
import com.saniikos.backend.repositories.DepartmentRepository;
import com.saniikos.backend.services.DepartmentService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepository,
            DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponse findById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id " + id));

        return departmentMapper.toResponse(department);
    }

    @Override
    public DepartmentResponse findByCode(String code) {
        Department department = departmentRepository.findByCode(code)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with code " + code));

        return departmentMapper.toResponse(department);
    }

    @Override
    public DepartmentResponse save(DepartmentRequest request) {

        if (departmentRepository.findByCode(request.getCode()).isPresent()) {
            throw new IllegalArgumentException("Department code already exists.");
        }

        Department department = departmentMapper.toEntity(request);

        department = departmentRepository.save(department);

        return departmentMapper.toResponse(department);
    }

    @Override
    public DepartmentResponse update(Long id, DepartmentRequest request) {

        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id " + id));

        departmentRepository.findByCode(request.getCode())
                .filter(department -> !department.getId().equals(id))
                .ifPresent(department -> {
                    throw new IllegalArgumentException("Department code already exists.");
                });

        departmentMapper.updateEntity(request, existing);

        existing = departmentRepository.save(existing);

        return departmentMapper.toResponse(existing);
    }

    @Override
    public void delete(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id " + id));

        if (Boolean.TRUE.equals(department.getSystem())) {
            throw new IllegalArgumentException("System departments cannot be deleted.");
        }

        department.setActive(false);

        departmentRepository.save(department);
    }
}