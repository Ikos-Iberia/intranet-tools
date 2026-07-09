package com.saniikos.backend.mappers;

import org.springframework.stereotype.Component;

import com.saniikos.backend.dto.department.DepartmentRequest;
import com.saniikos.backend.dto.department.DepartmentResponse;
import com.saniikos.backend.entities.Department;

@Component
public class DepartmentMapper {

    public DepartmentResponse toResponse(Department department) {

        if (department == null) {
            return null;
        }

        DepartmentResponse response = new DepartmentResponse();

        response.setId(department.getId());
        response.setCode(department.getCode());
        response.setName(department.getName());
        response.setDescription(department.getDescription());
        response.setSystem(department.getSystem());
        response.setActive(department.getActive());
        response.setCreatedAt(department.getCreatedAt());
        response.setUpdatedAt(department.getUpdatedAt());

        return response;
    }

    public Department toEntity(DepartmentRequest request) {

        if (request == null) {
            return null;
        }

        Department department = new Department();

        department.setCode(request.getCode());
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setActive(request.getActive());

        return department;
    }

    public void updateEntity(DepartmentRequest request, Department department) {

        department.setCode(request.getCode());
        department.setName(request.getName());
        department.setDescription(request.getDescription());
        department.setActive(request.getActive());

    }

}