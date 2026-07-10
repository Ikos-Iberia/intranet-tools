package com.saniikos.backend.mappers;

import org.springframework.stereotype.Component;

import com.saniikos.backend.dto.toolpermission.ToolPermissionResponse;
import com.saniikos.backend.entities.ToolPermission;

@Component
public class ToolPermissionMapper {

    public ToolPermissionResponse toResponse(ToolPermission toolPermission) {

        if (toolPermission == null) {
            return null;
        }

        ToolPermissionResponse response = new ToolPermissionResponse();

        response.setToolId(toolPermission.getTool().getId());
        response.setToolCode(toolPermission.getTool().getCode());
        response.setToolName(toolPermission.getTool().getName());

        response.setRoleId(toolPermission.getRole().getId());
        response.setRoleCode(toolPermission.getRole().getCode());
        response.setRoleName(toolPermission.getRole().getName());

        return response;
    }

}