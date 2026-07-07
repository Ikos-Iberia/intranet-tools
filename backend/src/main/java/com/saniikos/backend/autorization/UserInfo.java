package com.saniikos.backend.autorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saniikos.backend.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
	private Long id;
	private String username;
	@JsonIgnore
	private String password;
	private Role role;
}
