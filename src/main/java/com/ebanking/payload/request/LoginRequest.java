package com.ebanking.payload.request;
 
import com.ebanking.models.UserRole;

public class LoginRequest {
	 
	private String username;
 
	private String password;
    private UserRole role;
	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
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
