package com.saniikos.backend.dto.useractivity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "User activity request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityRequest {

    @Schema(description = "User id", example = "1")
    private Long userId;

    @Schema(description = "Tool id", example = "1")
    private Long toolId;

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
    private Boolean success = true;

}