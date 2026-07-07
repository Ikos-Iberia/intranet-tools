package com.saniikos.backend.autorization;

import lombok.Data;
import lombok.NoArgsConstructor;
import com.saniikos.backend.enums.Role;

@Data
@NoArgsConstructor
public class LoginResponseDTO {

	private Long id;
	private String token;
	private Role role;
	private String username;

	public LoginResponseDTO(String accessToken, Long id, String username, Role role) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.role = role;
	}
}
