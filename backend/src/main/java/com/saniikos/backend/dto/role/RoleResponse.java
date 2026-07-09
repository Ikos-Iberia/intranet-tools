package com.saniikos.backend.dto.role;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleResponse {

    private Long id;

    private String code;

    private String name;

    private String description;

    private Boolean system;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}