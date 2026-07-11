package com.saniikos.backend.dto.toolpermission;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Tool permission request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolPermissionRequest {

    @Schema(description = "Tool id", example = "1")
    @NotNull
    private Long toolId;

    @Schema(description = "Role id", example = "1")
    @NotNull
    private Long roleId;

}