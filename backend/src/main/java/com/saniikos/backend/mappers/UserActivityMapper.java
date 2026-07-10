package com.saniikos.backend.mappers;

import org.springframework.stereotype.Component;

import com.saniikos.backend.dto.useractivity.UserActivityResponse;
import com.saniikos.backend.entities.UserActivity;

@Component
public class UserActivityMapper {

    public UserActivityResponse toResponse(UserActivity activity) {

        if (activity == null) {
            return null;
        }

        UserActivityResponse response = new UserActivityResponse();

        response.setId(activity.getId());

        response.setUserId(activity.getUser().getId());
        response.setUsername(activity.getUser().getUsername());

        if (activity.getTool() != null) {
            response.setToolId(activity.getTool().getId());
            response.setToolName(activity.getTool().getName());
        }

        response.setActionCode(activity.getActionCode());
        response.setEntityType(activity.getEntityType());
        response.setEntityId(activity.getEntityId());

        response.setIpAddress(activity.getIpAddress());
        response.setUserAgent(activity.getUserAgent());

        response.setMetadata(activity.getMetadata());

        response.setSuccess(activity.getSuccess());

        response.setCreatedAt(activity.getCreatedAt());

        return response;
    }

}