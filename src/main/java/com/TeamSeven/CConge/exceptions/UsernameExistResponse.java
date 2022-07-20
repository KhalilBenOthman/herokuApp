package com.TeamSeven.CConge.exceptions;

public class UsernameExistResponse {
	
	private String username;

	public UsernameExistResponse(String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
