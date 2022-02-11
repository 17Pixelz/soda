package com.ebanking;

import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ebanking.dao.BeneficiaireRepository;
import com.ebanking.dao.ClientRepository;
import com.ebanking.dao.CompteBanquireRep;
import com.ebanking.dao.CompteClRepository;
import com.ebanking.dao.UserRepository;
import com.ebanking.dao.WalletRepository;
import com.ebanking.models.Beneficiaire;
import com.ebanking.models.Client;
import com.ebanking.models.CompteBanque;
import com.ebanking.models.CompteCl;
import com.ebanking.models.User;
import com.ebanking.models.Wallet;

@SpringBootApplication

public class AppGestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppGestionApplication.class, args);
	}
	 @Bean
	    CommandLineRunner start(ClientRepository cl,CompteClRepository compteRep,WalletRepository wr,UserRepository ur,CompteBanquireRep repc ,BeneficiaireRepository br ){
	        return args->{  
//	        	Client c=new Client();
//	        	User u=ur.findById(1).orElse(null);
//	        	 
//	        	c.setUser(u);
//	            cl.save(c); 
//	            CompteCl compte= new CompteCl();
//	        	 compte.setClient(c);
//	        	 compteRep.save(compte);
	        	//  CompteBanque compte= new CompteBanque();
	        	  //repc.save(compte);
	        	 // Beneficiaire b=new Beneficiaire();
//br.save(b);
//		        	 
            
//	           Wallet w =wr.findById(1).get();
//	           c.setWalletPorteur(w);
            
//	            
	            
};
	        }
	 
	 
}