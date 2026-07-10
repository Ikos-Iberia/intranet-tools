package com.saniikos.backend.dto.userrole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleResponse {

    private Long userId;

    private String username;

    private Long roleId;

    private String roleCode;

    private String roleName;

}