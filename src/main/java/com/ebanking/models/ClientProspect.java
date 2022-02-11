package com.ebanking.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientProspect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomclient;
    private String prenomClient;
    private String carteidentité;

    @OneToOne
    @JoinColumn(name="kyc_id")
    private KYC kyc;
    private Boolean is_kyc_done;

//    @OneToMany(mappedBy = "prospects",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//   private List<Transfert> transfertparAgents;

}
