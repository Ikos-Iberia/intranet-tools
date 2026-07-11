package com.saniikos.backend.dto.department;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Department response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {

    @Schema(description = "Department id", example = "1")
    private Long id;

    @Schema(description = "Department code", example = "HR")
    private String code;

    @Schema(description = "Department name", example = "Human Resources")
    private String name;

    @Schema(description = "Department description", example = "Manages all human resources activities")
    private String description;

    @Schema(description = "Department system flag", example = "true")
    private Boolean system;

    @Schema(description = "Department active flag", example = "true")
    private Boolean active;

    @Schema(description = "Department creation timestamp", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Department last update timestamp", example = "2023-01-01T00:00:00")
    private LocalDateTime updatedAt;

}