package com.ebanking.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
@DiscriminatorValue("admin")
public class Admin {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAgent;
	@OneToOne 
	 @JoinColumn(name="user_id")
	private User user;
	 @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<Agent> agents;

}
