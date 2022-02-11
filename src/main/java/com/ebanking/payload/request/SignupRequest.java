package com.ebanking.payload.request;

import java.util.Set;
 
import com.ebanking.models.UserRole; 
 
public class SignupRequest { 
    private String username;
 
    
    private UserRole role;
    
    
    
    public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
 
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
