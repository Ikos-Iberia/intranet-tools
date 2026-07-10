package com.saniikos.backend.dto.tooldepartment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolDepartmentRequest {

    @NotNull
    private Long toolId;

    @NotNull
    private Long departmentId;

}