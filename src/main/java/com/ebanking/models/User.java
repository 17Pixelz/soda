package com.ebanking.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username") 
		})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	@Enumerated(EnumType.STRING)
    private UserRole role;
	private String password;
	public User(String username, UserRole role, String password) {
		this.username = username;
		 this.role=role;
		this.password = password;
	}
 
}
