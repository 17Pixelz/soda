package com.ebanking.models;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CompteCl implements Serializable {
	 
    @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numeroCompte;
    private Double soldeCompte;
    private Date date_création;
    private Date date_premierTransfert;
    private Double somme_transferts_Année;
    @OneToOne
    @JoinColumn(name ="client")
    private Client client;


    @NotFound(action=NotFoundAction.IGNORE)
    @OneToOne
    @JoinColumn(name="wallet_id")
    private Wallet walletPorteur;
    @OneToMany(mappedBy = "transactionFromCompte")
    private List<WaletTransaction> walletTransactions;
    @OneToMany(mappedBy = "compteCl")
    private List<TransfertparClient> TransfertparClient;
    private   int nbrTransfertWallet=0;
    private   double montantTotalTransfert=0;
    @OneToMany(mappedBy = "unclientde")
    private List<Beneficiaire> beneficiaires;

}