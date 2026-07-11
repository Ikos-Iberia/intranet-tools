package com.saniikos.backend.dto.userrole;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "User role request")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequest {

    @Schema(description = "User id", example = "1")
    @NotNull
    private Long userId;

    @Schema(description = "Role id", example = "1")
    @NotNull
    private Long roleId;

}