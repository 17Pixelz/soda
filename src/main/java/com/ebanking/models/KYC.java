package com.ebanking.models;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KYC {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_kyc;
	
	private String	Titre;
	private String	Prenom;
	private String Nom;
	private String	Typepiece ;
		 
	private String	PaysEmission;
	private String	numPiece;
		 
	private Date	Validitepiece ;
		  
	private Date	Datenaissance;
	private String Profession;
	private String  PayNationalite;
	private String Payadresse;
	private String AdresseLegale;
	private String Ville;
	private String GSM;

    @Email(message = "Email should be valid")
	private String Email;
	@OneToOne
	private Client client;
	 
}
