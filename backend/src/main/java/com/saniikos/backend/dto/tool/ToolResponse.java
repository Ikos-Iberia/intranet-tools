package com.saniikos.backend.dto.tool;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Tool response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolResponse {

    @Schema(description = "Tool id", example = "1")
    private Long id;

    @Schema(description = "Tool code", example = "TOOL001")
    private String code;

    @Schema(description = "Tool name", example = "Tool Name")
    private String name;

    @Schema(description = "Tool description", example = "Tool description")
    private String description;

    @Schema(description = "Tool route", example = "/tool")
    private String route;

    @Schema(description = "Tool icon", example = "tool-icon")
    private String icon;

    @Schema(description = "Tool version", example = "1.0.0")
    private String version;

    @Schema(description = "Tool category id", example = "1")
    private Long categoryId;

    @Schema(description = "Tool category name", example = "Category Name")
    private String categoryName;

    @Schema(description = "Tool visibility id", example = "1")
    private Long visibilityId;

    @Schema(description = "Tool visibility name", example = "Visibility Name")
    private String visibilityName;

    @Schema(description = "Tool enabled flag", example = "true")
    private Boolean enabled;

    @Schema(description = "Tool display order", example = "1")
    private Integer displayOrder;

    @Schema(description = "Tool frontend only flag", example = "true")
    private Boolean frontendOnly;

    @Schema(description = "Tool created at", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Tool updated at", example = "2023-01-01T00:00:00")
    private LocalDateTime updatedAt;

}