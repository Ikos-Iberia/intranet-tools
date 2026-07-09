package com.saniikos.backend.mappers;

import org.springframework.stereotype.Component;

import com.saniikos.backend.dto.role.RoleRequest;
import com.saniikos.backend.dto.role.RoleResponse;
import com.saniikos.backend.entities.Role;

@Component
public class RoleMapper {

    public RoleResponse toResponse(Role role) {

        if (role == null) {
            return null;
        }

        RoleResponse response = new RoleResponse();

        response.setId(role.getId());
        response.setCode(role.getCode());
        response.setName(role.getName());
        response.setDescription(role.getDescription());
        response.setSystem(role.getSystem());
        response.setActive(role.getActive());
        response.setCreatedAt(role.getCreatedAt());
        response.setUpdatedAt(role.getUpdatedAt());

        return response;
    }

    public Role toEntity(RoleRequest request) {

        if (request == null) {
            return null;
        }

        Role role = new Role();

        role.setCode(request.getCode());
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setActive(request.getActive());

        return role;
    }

    public void updateEntity(RoleRequest request, Role role) {

        role.setCode(request.getCode());
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setActive(request.getActive());

    }
}