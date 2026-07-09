package com.saniikos.backend.services;

import java.util.List;

import com.saniikos.backend.dto.role.RoleRequest;
import com.saniikos.backend.dto.role.RoleResponse;

public interface RoleService {

    List<RoleResponse> findAll();

    RoleResponse findById(Long id);

    RoleResponse findByCode(String code);

    RoleResponse save(RoleRequest request);

    RoleResponse update(Long id, RoleRequest request);

    void delete(Long id);

}