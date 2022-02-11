package com.ebanking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@AllArgsConstructor
@Entity
@DiscriminatorColumn(name = "Type")
public  class Transfert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int transactionId;
    protected static Double static_referenceTransfert=8370000000000.00;
    protected Double referenceTransfert;
    protected String description;
    protected Date datedeTransfert;
    protected Date dateArrivé;
    protected String typeFrais;
    protected String typeTransfert;
    protected Double montantTransfert;
    @Enumerated(EnumType.STRING)
    protected Etat etat;
    protected Double commission;
    protected Double plafond;
    protected boolean notification;
    protected Boolean transfert_guichet;
    protected String motif;
    @ManyToOne
    protected CompteCl compteCl;
    @ManyToOne
    protected CompteAgent compteAgent;
    @ManyToOne
    protected Beneficiaire beneficiare;
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne
    protected Guichet guichet;

}
