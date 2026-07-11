package com.saniikos.backend.dto.userrole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "User role response")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleResponse {

    @Schema(description = "User id", example = "1")
    private Long userId;

    @Schema(description = "Username", example = "johndoe")
    private String username;

    @Schema(description = "Role id", example = "1")
    private Long roleId;

    @Schema(description = "Role code", example = "ADMIN")
    private String roleCode;

    @Schema(description = "Role name", example = "Administrator")
    private String roleName;

}