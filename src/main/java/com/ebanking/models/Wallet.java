package com.ebanking.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet implements Serializable {

	 @Id

     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int walletId;
	 
	 private Boolean is_active;
	      
	 @OneToOne(mappedBy = "walletPorteur")
	    private CompteCl comptInWallet;
	  
}
