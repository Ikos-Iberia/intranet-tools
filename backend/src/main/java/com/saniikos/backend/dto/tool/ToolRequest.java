package com.saniikos.backend.dto.tool;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Tool request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolRequest {

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

    @Schema(description = "Tool route", example = "/tool")
    @NotBlank
    private String route;

    @Schema(description = "Tool icon", example = "tool-icon")
    private String icon;

    @Schema(description = "Tool version", example = "1.0.0")
    private String version;

    @Schema(description = "Tool category id", example = "1")
    @NotNull
    private Long categoryId;

    @Schema(description = "Tool visibility id", example = "1")
    @NotNull
    private Long visibilityId;

    @Schema(description = "Tool enabled flag", example = "true")
    private Boolean enabled;

    @Schema(description = "Tool display order", example = "1")
    private Integer displayOrder;

    @Schema(description = "Tool frontend only flag", example = "true")
    private Boolean frontendOnly;
}