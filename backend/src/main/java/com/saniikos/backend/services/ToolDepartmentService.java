package com.saniikos.backend.services;

import java.util.List;

import com.saniikos.backend.dto.tooldepartment.ToolDepartmentRequest;
import com.saniikos.backend.dto.tooldepartment.ToolDepartmentResponse;

public interface ToolDepartmentService {

    List<ToolDepartmentResponse> findAll();

    List<ToolDepartmentResponse> findByTool(Long toolId);

    List<ToolDepartmentResponse> findByDepartment(Long departmentId);

    ToolDepartmentResponse save(ToolDepartmentRequest request);

    void delete(Long toolId, Long departmentId);

}