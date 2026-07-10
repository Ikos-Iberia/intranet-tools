package com.saniikos.backend.dto.useractivity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityResponse {

    private Long id;

    private Long userId;

    private String username;

    private Long toolId;

    private String toolName;

    private String actionCode;

    private String entityType;

    private Long entityId;

    private String ipAddress;

    private String userAgent;

    private String metadata;

    private Boolean success;

    private LocalDateTime createdAt;

}