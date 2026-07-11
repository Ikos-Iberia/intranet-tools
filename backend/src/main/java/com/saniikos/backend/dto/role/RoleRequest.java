package com.saniikos.backend.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Role request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {

    @Schema(description = "Role code", example = "ADMIN")
    @NotBlank
    @Size(max = 50)
    private String code;

    @Schema(description = "Role name", example = "Administrator")
    @NotBlank
    @Size(max = 100)
    private String name;

    @Schema(description = "Role description", example = "Has full access to all system features")
    private String description;

    @Schema(description = "Role active flag", example = "true")
    private Boolean active;

}