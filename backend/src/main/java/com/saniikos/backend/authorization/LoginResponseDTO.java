package com.saniikos.backend.authorization;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResponseDTO {

	private Long id;

	private String username;

	private Set<String> roles;

	private String token;

	public LoginResponseDTO(String token, Long id, String username, Set<String> roles) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.roles = roles;
	}

}