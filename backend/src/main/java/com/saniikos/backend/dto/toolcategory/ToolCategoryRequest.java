package com.saniikos.backend.dto.toolcategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Tool category request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolCategoryRequest {

    @Schema(description = "Tool category code", example = "CAT001")
    @NotBlank
    @Size(max = 50)
    private String code;

    @Schema(description = "Tool category name", example = "Category Name")
    @NotBlank
    @Size(max = 100)
    private String name;

    @Schema(description = "Tool category description", example = "Category description")
    private String description;

    @Schema(description = "Tool category display order", example = "1")
    private Integer displayOrder;

    @Schema(description = "Tool category active flag", example = "true")
    private Boolean active;

}