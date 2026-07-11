package com.saniikos.backend.dto.toolvisibility;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Tool visibility response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolVisibilityResponse {

    @Schema(description = "Tool visibility id", example = "1")
    private Long id;

    @Schema(description = "Tool code", example = "TOOL001")
    private String code;

    @Schema(description = "Tool name", example = "Tool Name")
    private String name;

    @Schema(description = "Tool description", example = "Tool description")
    private String description;

    @Schema(description = "Creation date", example = "2023-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "Update date", example = "2023-01-01T00:00:00")
    private LocalDateTime updatedAt;

}