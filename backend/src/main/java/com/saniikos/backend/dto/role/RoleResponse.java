package com.saniikos.backend.dto.role;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Role response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {

    @Schema(description = "Role id", example = "1")
    private Long id;

    @Schema(description = "Role code", example = "ADMIN")
    private String code;

    @Schema(description = "Role name", example = "Administrator")
    private String name;

    @Schema(description = "Role description", example = "Has full access to all system features and settings.")
    private String description;

    @Schema(description = "Role system flag", example = "true")
    private Boolean system;

    @Schema(description = "Role active flag", example = "true")
    private Boolean active;

    @Schema(description = "Role created at", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Role updated at", example = "2023-01-01T00:00:00")
    private LocalDateTime updatedAt;

}