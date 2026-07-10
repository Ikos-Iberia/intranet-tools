package com.saniikos.backend.mappers;

import org.springframework.stereotype.Component;

import com.saniikos.backend.dto.tool.ToolRequest;
import com.saniikos.backend.dto.tool.ToolResponse;
import com.saniikos.backend.entities.Tool;

@Component
public class ToolMapper {

    public ToolResponse toResponse(Tool tool) {

        if (tool == null) {
            return null;
        }

        ToolResponse response = new ToolResponse();

        response.setId(tool.getId());
        response.setCode(tool.getCode());
        response.setName(tool.getName());
        response.setDescription(tool.getDescription());
        response.setRoute(tool.getRoute());
        response.setIcon(tool.getIcon());
        response.setVersion(tool.getVersion());

        response.setCategoryId(tool.getCategory().getId());
        response.setCategoryName(tool.getCategory().getName());

        response.setVisibilityId(tool.getVisibility().getId());
        response.setVisibilityName(tool.getVisibility().getName());

        response.setEnabled(tool.getEnabled());
        response.setDisplayOrder(tool.getDisplayOrder());
        response.setFrontendOnly(tool.getFrontendOnly());

        response.setCreatedAt(tool.getCreatedAt());
        response.setUpdatedAt(tool.getUpdatedAt());

        return response;
    }

    public Tool toEntity(ToolRequest request) {

        if (request == null) {
            return null;
        }

        Tool tool = new Tool();

        tool.setCode(request.getCode());
        tool.setName(request.getName());
        tool.setDescription(request.getDescription());
        tool.setRoute(request.getRoute());
        tool.setIcon(request.getIcon());
        tool.setVersion(request.getVersion());
        tool.setEnabled(request.getEnabled());
        tool.setDisplayOrder(request.getDisplayOrder());
        tool.setFrontendOnly(request.getFrontendOnly());

        return tool;
    }

    public void updateEntity(ToolRequest request, Tool tool) {

        tool.setCode(request.getCode());
        tool.setName(request.getName());
        tool.setDescription(request.getDescription());
        tool.setRoute(request.getRoute());
        tool.setIcon(request.getIcon());
        tool.setVersion(request.getVersion());
        tool.setEnabled(request.getEnabled());
        tool.setDisplayOrder(request.getDisplayOrder());
        tool.setFrontendOnly(request.getFrontendOnly());

    }

}