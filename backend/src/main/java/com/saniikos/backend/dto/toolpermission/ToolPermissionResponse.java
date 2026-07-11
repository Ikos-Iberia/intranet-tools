package com.saniikos.backend.dto.toolpermission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Tool permission response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolPermissionResponse {

    @Schema(description = "Tool id", example = "1")
    private Long toolId;

    @Schema(description = "Tool code", example = "TOOL001")
    private String toolCode;

    @Schema(description = "Tool name", example = "Tool Name")
    private String toolName;

    @Schema(description = "Role id", example = "1")
    private Long roleId;

    @Schema(description = "Role code", example = "ROLE001")
    private String roleCode;

    @Schema(description = "Role name", example = "Role Name")
    private String roleName;

}