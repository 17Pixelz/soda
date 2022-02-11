package com.ebanking.models;
 
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.ebanking.constant.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore; 

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor; 
@Entity
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class WaletTransaction {
	  @Id //to set as primary key
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // to set as autoincrement
	    private Integer transactionId;

    @Column(name = "local_date_time", columnDefinition = "TIMESTAMP")
	    private LocalDateTime dateT;
	    private double amount;
	    private double plafont=Constants.PLAFONT_TRANSFERT_WALLET ; 
	    private int typeFrais;
        private boolean notification;
        private double plafontAnn=Constants.PLAFONT_TRANSFERT_WALLET_ANNUEL;
	    @ManyToOne 
	    private CompteCl transactionFromCompte; 
	    @ManyToOne
	    private Beneficiaire b;
	    @Enumerated(EnumType.STRING)
	    private Etat etat;
	    private long delai;
}
