package com.saniikos.backend.dto.tool;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolResponse {

    private Long id;

    private String code;

    private String name;

    private String description;

    private String route;

    private String icon;

    private String version;

    private Long categoryId;

    private String categoryName;

    private Long visibilityId;

    private String visibilityName;

    private Boolean enabled;

    private Integer displayOrder;

    private Boolean frontendOnly;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}