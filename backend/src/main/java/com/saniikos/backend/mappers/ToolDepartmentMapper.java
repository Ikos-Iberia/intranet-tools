package com.saniikos.backend.mappers;

import org.springframework.stereotype.Component;

import com.saniikos.backend.dto.tooldepartment.ToolDepartmentResponse;
import com.saniikos.backend.entities.ToolDepartment;

@Component
public class ToolDepartmentMapper {

    public ToolDepartmentResponse toResponse(ToolDepartment toolDepartment) {

        if (toolDepartment == null) {
            return null;
        }

        ToolDepartmentResponse response = new ToolDepartmentResponse();

        response.setToolId(toolDepartment.getTool().getId());
        response.setToolCode(toolDepartment.getTool().getCode());
        response.setToolName(toolDepartment.getTool().getName());

        response.setDepartmentId(toolDepartment.getDepartment().getId());
        response.setDepartmentCode(toolDepartment.getDepartment().getCode());
        response.setDepartmentName(toolDepartment.getDepartment().getName());

        return response;
    }

}