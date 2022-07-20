package com.TeamSeven.CConge.exceptions;

public class InvalidLoginResponse {
	private String username;
	private String password;
	public InvalidLoginResponse() {
		super();
		this.username = "invalide username";
		this.password = "invalide password";
	}
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
