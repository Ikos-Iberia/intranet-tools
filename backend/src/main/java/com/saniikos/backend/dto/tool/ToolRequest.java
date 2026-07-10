package com.saniikos.backend.dto.tool;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolRequest {

    @NotBlank
    @Size(max = 50)
    private String code;

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;

    @NotBlank
    private String route;

    private String icon;

    private String version;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long visibilityId;

    private Boolean enabled;

    private Integer displayOrder;

    private Boolean frontendOnly;
}