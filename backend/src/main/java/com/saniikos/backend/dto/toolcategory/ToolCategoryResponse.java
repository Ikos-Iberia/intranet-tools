package com.saniikos.backend.dto.toolcategory;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Tool category response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolCategoryResponse {

    @Schema(description = "Tool category id", example = "1")
    private Long id;

    @Schema(description = "Tool category code", example = "CAT001")
    private String code;

    @Schema(description = "Tool category name", example = "Category Name")
    private String name;

    @Schema(description = "Tool category description", example = "Category description")
    private String description;

    @Schema(description = "Tool category system flag", example = "true")
    private Boolean system;

    @Schema(description = "Tool category active flag", example = "true")
    private Boolean active;

    @Schema(description = "Tool category display order", example = "1")
    private Integer displayOrder;

    @Schema(description = "Tool category created at", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Tool category updated at", example = "2023-01-01T00:00:00")
    private LocalDateTime updatedAt;

}