package com.saniikos.backend.dto.tooldepartment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Tool department response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolDepartmentResponse {

    @Schema(description = "Tool id", example = "1")
    private Long toolId;

    @Schema(description = "Tool code", example = "TOOL001")
    private String toolCode;

    @Schema(description = "Tool name", example = "Tool Name")
    private String toolName;

    @Schema(description = "Department id", example = "1")
    private Long departmentId;

    @Schema(description = "Department code", example = "DEPT001")
    private String departmentCode;

    @Schema(description = "Department name", example = "Department Name")
    private String departmentName;

}