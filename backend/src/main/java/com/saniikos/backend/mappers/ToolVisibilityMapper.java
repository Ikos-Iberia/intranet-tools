package com.saniikos.backend.mappers;

import org.springframework.stereotype.Component;

import com.saniikos.backend.dto.toolvisibility.ToolVisibilityRequest;
import com.saniikos.backend.dto.toolvisibility.ToolVisibilityResponse;
import com.saniikos.backend.entities.ToolVisibility;

@Component
public class ToolVisibilityMapper {

    public ToolVisibilityResponse toResponse(ToolVisibility visibility) {

        if (visibility == null) {
            return null;
        }

        ToolVisibilityResponse response = new ToolVisibilityResponse();

        response.setId(visibility.getId());
        response.setCode(visibility.getCode());
        response.setName(visibility.getName());
        response.setDescription(visibility.getDescription());
        response.setCreatedAt(visibility.getCreatedAt());
        response.setUpdatedAt(visibility.getUpdatedAt());

        return response;
    }

    public ToolVisibility toEntity(ToolVisibilityRequest request) {

        if (request == null) {
            return null;
        }

        ToolVisibility visibility = new ToolVisibility();

        visibility.setCode(request.getCode());
        visibility.setName(request.getName());
        visibility.setDescription(request.getDescription());

        return visibility;
    }

    public void updateEntity(ToolVisibilityRequest request, ToolVisibility visibility) {

        visibility.setCode(request.getCode());
        visibility.setName(request.getName());
        visibility.setDescription(request.getDescription());

    }

}