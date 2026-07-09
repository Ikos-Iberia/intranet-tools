package com.saniikos.backend.services;

import java.util.List;

import com.saniikos.backend.dto.department.DepartmentRequest;
import com.saniikos.backend.dto.department.DepartmentResponse;

public interface DepartmentService {

    List<DepartmentResponse> findAll();

    DepartmentResponse findById(Long id);

    DepartmentResponse findByCode(String code);

    DepartmentResponse save(DepartmentRequest request);

    DepartmentResponse update(Long id, DepartmentRequest request);

    void delete(Long id);

}