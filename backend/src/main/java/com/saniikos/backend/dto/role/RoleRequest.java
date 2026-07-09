package com.saniikos.backend.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {

    @NotBlank
    @Size(max = 50)
    private String code;

    @NotBlank
    @Size(max = 100)
    private String name;

    private String description;

    private Boolean active;

}