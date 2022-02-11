package com.ebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransfertRequest {
   private String identifiant ;
    private int iduser;
    private double montant;
    private boolean notif;
    private int typedefrais;
    private int idbeneficiaire;
    private int delai;
    private String motif;
}
