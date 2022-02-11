package com.ebanking.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import com.ebanking.constant.Functions;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("TransfertparClient")
public class TransfertparClient extends Transfert {


    public TransfertparClient(int delaiparheures, CompteCl compteCl, Beneficiaire beneficiaire,String typedetransfert,String typedefrais, Double montant, Double montantTransfert){

        this.etat=Etat.A_servir;
        this.datedeTransfert=new Date();
        this.compteCl=compteCl;
        this.beneficiare=beneficiaire;
        this.dateArrivé= Functions.addHoursToJavaUtilDate(datedeTransfert,delaiparheures);
        static_referenceTransfert++;
        referenceTransfert=static_referenceTransfert;
        this.description="Information about Transaction: Reference : "+referenceTransfert+"  From:" +
                " "+compteCl.getClient().getNomclient()+" To :"+beneficiaire.getNom() +"Send in " +datedeTransfert +" and will be available in " + dateArrivé ;
        this.typeFrais=typedefrais;
        this.typeTransfert=typedetransfert;
        if(compteCl.getDate_premierTransfert()==null){
            compteCl.setDate_premierTransfert(datedeTransfert);
        }

    }

}
