package com.saniikos.backend.dto.userrole;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long roleId;

}