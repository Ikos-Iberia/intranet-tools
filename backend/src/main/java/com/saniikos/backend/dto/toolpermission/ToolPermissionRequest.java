package com.saniikos.backend.dto.toolpermission;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolPermissionRequest {

    @NotNull
    private Long toolId;

    @NotNull
    private Long roleId;

}