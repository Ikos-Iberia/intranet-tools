package com.saniikos.backend.dto.toolpermission;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolPermissionResponse {

    private Long toolId;

    private String toolCode;

    private String toolName;

    private Long roleId;

    private String roleCode;

    private String roleName;

}