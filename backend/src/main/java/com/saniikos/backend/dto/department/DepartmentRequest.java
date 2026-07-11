package com.saniikos.backend.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Department request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRequest {

    @Schema(description = "Department code", example = "IT")
    @NotBlank
    @Size(max = 50)
    private String code;
    
    @Schema(description = "Department name", example = "Information Technology")
    @NotBlank
    @Size(max = 100)
    private String name;

    @Schema(description = "Department description", example = "Handles all IT related tasks and support.")
    private String description;

    @Schema(description = "Department active flag", example = "true")
    private Boolean active;

}