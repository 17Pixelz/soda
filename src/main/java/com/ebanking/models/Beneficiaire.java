package com.ebanking.models;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiaire {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String nom;
	    private String prenom;
	    private String email;
	    private String phone;
	   private Boolean is_kyc_done;

	@OneToMany(mappedBy = "beneficiare", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TransfertparClient> transfertparClient;

	 @OneToOne 
	 @JoinColumn(name="kyc_id")
	 private KYC kyc;

	 public Beneficiaire(String nom,String prenom,String telnumber){
			this.nom=nom;
			this.prenom=prenom;
			this.phone=telnumber;
	 }
	public Beneficiaire(String nom,String prenom){
		this.nom=nom;
		this.prenom=prenom;
	}

	@ManyToOne
	@JoinColumn(name = "unclientde_id")
	private CompteCl unclientde;
}
