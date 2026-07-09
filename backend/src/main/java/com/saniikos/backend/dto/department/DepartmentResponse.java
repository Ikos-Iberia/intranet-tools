package com.saniikos.backend.dto.department;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentResponse {

    private Long id;

    private String code;

    private String name;

    private String description;

    private Boolean system;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}