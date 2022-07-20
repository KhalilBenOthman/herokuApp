package com.TeamSeven.CConge.payload;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

	@NotBlank(message="Username est obligatoire")
	private String username;
	@NotBlank(message="password est obligatoire")
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	
}
