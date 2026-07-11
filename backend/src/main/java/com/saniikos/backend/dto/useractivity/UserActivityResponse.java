package com.saniikos.backend.dto.useractivity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "User activity response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityResponse {

    @Schema(description = "User activity id", example = "1")
    private Long id;

    @Schema(description = "User id", example = "1")
    private Long userId;

    @Schema(description = "Username", example = "johndoe")
    private String username;

    @Schema(description = "Tool id", example = "1")
    private Long toolId;

    @Schema(description = "Tool name", example = "Tool Name")
    private String toolName;

    @Schema(description = "Action code", example = "LOGIN")
    private String actionCode;

    @Schema(description = "Entity type", example = "USER")
    private String entityType;

    @Schema(description = "Entity id", example = "1")
    private Long entityId;

    @Schema(description = "IP address", example = "192.168.1.1")
    private String ipAddress;

    @Schema(description = "User agent", example = "Mozilla/5.0...")
    private String userAgent;

    @Schema(description = "Metadata", example = "{}")
    private String metadata;

    @Schema(description = "Success status", example = "true")
    private Boolean success;

    @Schema(description = "Created at", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

}