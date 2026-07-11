package com.saniikos.backend.dto.tooldepartment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Tool department request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolDepartmentRequest {

    @Schema(description = "Tool id", example = "1")
    @NotNull
    private Long toolId;

    @Schema(description = "Department id", example = "1")
    @NotNull
    private Long departmentId;

}