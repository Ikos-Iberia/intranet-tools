package com.saniikos.backend.dto.tooldepartment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolDepartmentResponse {

    private Long toolId;

    private String toolCode;

    private String toolName;

    private Long departmentId;

    private String departmentCode;

    private String departmentName;

}