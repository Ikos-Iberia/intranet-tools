package com.saniikos.backend.dto.toolvisibility;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Tool visibility request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolVisibilityRequest {

    @Schema(description = "Tool code", example = "TOOL001")
    @NotBlank
    @Size(max = 50)
    private String code;

    @Schema(description = "Tool name", example = "Tool Name")
    @NotBlank
    @Size(max = 100)
    private String name;

    @Schema(description = "Tool description", example = "Tool description")
    private String description;

}