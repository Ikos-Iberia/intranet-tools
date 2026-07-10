package com.saniikos.backend.dto.useractivity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityRequest {

    private Long userId;

    private Long toolId;

    private String actionCode;

    private String entityType;

    private Long entityId;

    private String ipAddress;

    private String userAgent;

    private String metadata;

    private Boolean success = true;

}