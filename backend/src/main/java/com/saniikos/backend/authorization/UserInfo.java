package com.saniikos.backend.authorization;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

	private Long id;

	private String username;

	@JsonIgnore
	private String password;

	private Set<String> roles;

}