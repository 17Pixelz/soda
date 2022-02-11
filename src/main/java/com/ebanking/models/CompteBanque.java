package com.ebanking.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class CompteBanque {
	 
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	  
	 private int id;
	    private int numeroCompte;
	    private double soldeCompte; 
	     
}
