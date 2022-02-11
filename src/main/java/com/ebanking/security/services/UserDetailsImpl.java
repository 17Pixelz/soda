package com.ebanking.security.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ebanking.models.User;
import com.ebanking.models.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int id;

	private String username;

	private UserRole role;

	public UserRole getRole() {
		return role;
	}

	@JsonIgnore
	private String password;
 

	public UserDetailsImpl(int id, String username, UserRole role, String password
			 
			) {
		this.id = id;
		this.username = username;
		this.role = role;
		this.password = password; 
	}

 	public static UserDetailsImpl build(User user) {
 
		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(), 
				user.getRole(),
				user.getPassword()
				 
				);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null; 
	}

	public int getId() {
		return id;
	}

	 
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
