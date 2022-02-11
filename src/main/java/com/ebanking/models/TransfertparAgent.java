package com.ebanking.models;

import com.ebanking.constant.Functions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@DiscriminatorValue("TransfertparAgent")
public class TransfertparAgent extends Transfert {


    public TransfertparAgent(int delaiparheurs,Client client, CompteAgent compteAgent, Beneficiaire beneficiaire,String typedetransfert, String typedefrais, Double montant, Double montantTransfert){
        this.datedeTransfert=new Date();
        this.compteAgent=compteAgent;
        this.etat=Etat.A_servir;
        this.beneficiare=beneficiaire;
        static_referenceTransfert++;
        referenceTransfert=static_referenceTransfert;
        this.dateArrivé= Functions.addHoursToJavaUtilDate(datedeTransfert,delaiparheurs);
        this.description="Information about transfer: Reference : "+referenceTransfert+"  From : " +
                " "+client.getNomclient()+" To :"+beneficiaire.getNom() +" in " +datedeTransfert +"  and will be available in " + dateArrivé ;
        this.typeFrais=typedefrais;
        this.typeTransfert=typedetransfert;
    }

}
