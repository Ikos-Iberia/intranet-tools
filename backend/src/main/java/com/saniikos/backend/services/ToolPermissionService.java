package com.saniikos.backend.services;

import java.util.List;

import com.saniikos.backend.dto.toolpermission.ToolPermissionRequest;
import com.saniikos.backend.dto.toolpermission.ToolPermissionResponse;

public interface ToolPermissionService {

    List<ToolPermissionResponse> findAll();

    List<ToolPermissionResponse> findByTool(Long toolId);

    List<ToolPermissionResponse> findByRole(Long roleId);

    ToolPermissionResponse save(ToolPermissionRequest request);

    void delete(Long toolId, Long roleId);

}