package com.ebanking.payload.response;

import java.util.List;

import com.ebanking.models.UserRole;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int id;
	private String username;
	private UserRole role;
	 
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public JwtResponse(String accessToken, int i, String username, UserRole role 
			 
			) {
		this.token = accessToken;
		this.id = i;
		this.username = username;
		this.role = role; 
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

 
}
