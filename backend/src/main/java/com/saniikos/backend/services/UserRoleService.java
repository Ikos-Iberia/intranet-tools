package com.saniikos.backend.services;

import java.util.List;

import com.saniikos.backend.dto.userrole.UserRoleRequest;
import com.saniikos.backend.dto.userrole.UserRoleResponse;

public interface UserRoleService {

    List<UserRoleResponse> findAll();

    List<UserRoleResponse> findByUser(Long userId);

    List<UserRoleResponse> findByRole(Long roleId);

    UserRoleResponse save(UserRoleRequest request);

    void delete(Long userId, Long roleId);

}