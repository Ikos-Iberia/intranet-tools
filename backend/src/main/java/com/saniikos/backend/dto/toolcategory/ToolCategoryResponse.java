package com.saniikos.backend.dto.toolcategory;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ToolCategoryResponse {

    private Long id;

    private String code;

    private String name;

    private String description;

    private Boolean system;

    private Boolean active;

    private Integer displayOrder;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}