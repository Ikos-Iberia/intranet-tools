package com.saniikos.backend.mappers;

import org.springframework.stereotype.Component;

import com.saniikos.backend.dto.user.UserRequest;
import com.saniikos.backend.dto.user.UserResponse;
import com.saniikos.backend.entities.User;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {

        if (user == null) {
            return null;
        }

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        if (user.getDepartment() != null) {
            response.setDepartmentId(user.getDepartment().getId());
            response.setDepartmentCode(user.getDepartment().getCode());
            response.setDepartmentName(user.getDepartment().getName());
        }

        response.setActive(user.getActive());
        response.setLastLogin(user.getLastLogin());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;
    }

    public User toEntity(UserRequest request) {

        if (request == null) {
            return null;
        }

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // La contraseña se encripta en el Service
        // user.setPasswordHash(...)

        user.setActive(request.getActive());

        return user;
    }

    public void updateEntity(UserRequest request, User user) {

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setActive(request.getActive());

        // La contraseña y el departamento se gestionan en el Service
    }

}