package com.ebanking.models;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Client {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int iduser;
 private String nomclient;
 private String email;
 private String prenomClient;

 @OneToOne
 @JoinColumn(name = "user_id")
 private User user;

 @OneToOne(mappedBy = "client")
 @JoinColumn(name = "kyc_id")
 private KYC kyc;

 private Boolean is_kyc_done;
 @OneToOne(mappedBy = "client")
 private CompteCl compte;
}


