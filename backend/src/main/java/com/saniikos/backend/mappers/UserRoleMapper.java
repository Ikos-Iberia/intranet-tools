package com.saniikos.backend.mappers;

import org.springframework.stereotype.Component;

import com.saniikos.backend.dto.userrole.UserRoleResponse;
import com.saniikos.backend.entities.UserRole;

@Component
public class UserRoleMapper {

    public UserRoleResponse toResponse(UserRole userRole) {

        if (userRole == null) {
            return null;
        }

        UserRoleResponse response = new UserRoleResponse();

        response.setUserId(userRole.getUser().getId());
        response.setUsername(userRole.getUser().getUsername());

        response.setRoleId(userRole.getRole().getId());
        response.setRoleCode(userRole.getRole().getCode());
        response.setRoleName(userRole.getRole().getName());

        return response;
    }

}