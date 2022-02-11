package com.ebanking.controllers;
import com.ebanking.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ebanking.Metier.WalletService; 
import com.ebanking.exception.CustomerAlreadyHasWalletException;
import com.ebanking.exception.CustomerDoesNotExistException;
import com.ebanking.exception.ExpirationDelai;
import com.ebanking.exception.InsuffisantSoldWalletException;
import com.ebanking.exception.OutOfLimitAmountTrnsfert;
import com.ebanking.exception.OutOfLimitNumberTransfert;
import com.ebanking.exception.TransfertDoesNotExistException;
import com.ebanking.exception.TransfertEstBloqué;
import com.ebanking.exception.TransfertEstPayé;
import com.ebanking.exception.WalletIdDoesNotExistException;
import com.ebanking.models.CompteCl;
import com.ebanking.models.ServiceResponse;
import com.ebanking.models.WaletTransaction;
import com.ebanking.models.Wallet;

@RestController
 
public class WalletController {

    @Autowired
    WalletService walletService;

    // Create a new wallet for a user. Constraint : A user can have only one wallet
    @PostMapping("/wallet/{customerId}")
    public ResponseEntity<ServiceResponse> createWallet(@PathVariable("customerId") int customerId) throws CustomerAlreadyHasWalletException {

        ServiceResponse response = new ServiceResponse();

        try {
            Wallet newWallet = walletService.createWallet(customerId);
            response.setStatus("200");
            response.setDescription("Wallet created successfully!");
            response.setData(newWallet);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(CustomerDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        catch(CustomerAlreadyHasWalletException e) {
            response.setStatus(String.valueOf(HttpStatus.EXPECTATION_FAILED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }

    }

     @GetMapping("/wallet/{walletId}")
    public  ResponseEntity<ServiceResponse>  getCompteSolde (
            @PathVariable("walletId") int walletId){



        ServiceResponse response = new ServiceResponse();

        try {
            Double balance = walletService.getSoldeCompteForCurrentWallet(walletId);
            response.setStatus("200");
            response.setDescription("solde trouvé avec succée!!");
            response.setData(balance);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(WalletIdDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
      

    }

    @PostMapping("/wallet/{walletId}/retrait/{montant}")
    public ResponseEntity<ServiceResponse> withdraw(
            @PathVariable("walletId") int walletId, 
            @PathVariable("montant") double montant) {

        ServiceResponse response = new ServiceResponse();

        try {
            walletService.retraitDeCompte(walletId,montant);
            response.setStatus("200");
            response.setDescription("montant "+ montant+ " withdrawn successfully!!");
          
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(WalletIdDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
        catch(InsuffisantSoldWalletException e) {
            response.setStatus(String.valueOf(HttpStatus.EXPECTATION_FAILED));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }

    }
 
    @PostMapping("/wallet/{walletId}/deposit/{montant}")
    public ResponseEntity<ServiceResponse> deposit(@PathVariable("walletId") int walletId,
                          
                         @PathVariable("montant") double montant) {


        ServiceResponse response = new ServiceResponse();

        try {  walletService.virement(walletId,montant);
            response.setStatus("200");
            response.setDescription("montant "+ montant+ " deposited successfully!!");
            
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(WalletIdDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        
    }

    @PostMapping("/transfert/{transfertId}/Agent/{agentId}")
    public ResponseEntity<ServiceResponse> servirtransfer(@PathVariable ("transfertId") int transfertId,
    		@PathVariable ("agentId") int agentId) 
    				throws ExpirationDelai, TransfertEstBloqué, TransfertDoesNotExistException, TransfertEstPayé
    {
    	
    	ServiceResponse response = new ServiceResponse();

      try {
            walletService.servirTransfertWallet(transfertId,agentId);
            response.setStatus("200");
            response.setDescription("tranfert  est payé!!");
            
            return new ResponseEntity<>(response, HttpStatus.OK);
      }
      catch(ExpirationDelai e) {
    	  response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
          response.setDescription(e.getMessage());
          return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      
      }
      catch(TransfertEstBloqué e) {
    	  response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
          response.setDescription(e.getMessage());
          return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      
      }
      catch(TransfertDoesNotExistException e) {
    	  response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
          response.setDescription(e.getMessage());
          return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      
      }
      catch(TransfertEstPayé e) {
    	  response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
          response.setDescription(e.getMessage());
          return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      
      }
    }

    @PostMapping("/wallet/{fromWalletId}/transfer/benificiaire/{toBenificiareId}/typeFrais/{typeFrais}/montant/{montant}/notification/{notif}")
    public ResponseEntity<ServiceResponse> transfer(@PathVariable ("fromWalletId") int fromWalletId,

            @PathVariable ("notif") boolean notif, 
                          @PathVariable ("toBenificiareId") int toBenificiareId, 
                          @PathVariable ("typeFrais") int typeFrais,
                          @PathVariable ("montant") double montant) throws WalletIdDoesNotExistException,
             InsuffisantSoldWalletException, OutOfLimitAmountTrnsfert, OutOfLimitNumberTransfert {

        ServiceResponse response = new ServiceResponse();

        try {
            walletService.transferVersCompte(fromWalletId,toBenificiareId, montant,typeFrais,notif);
            response.setStatus("200");
            response.setDescription("Amount "+ montant+ " transferred successfully!!");
            response.setData(montant);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(WalletIdDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
         }
        
         catch(InsuffisantSoldWalletException e) {
             response.setStatus(String.valueOf(HttpStatus.EXPECTATION_FAILED));
             response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
        }
        catch(OutOfLimitAmountTrnsfert e) {
            response.setStatus(String.valueOf(HttpStatus.EXPECTATION_FAILED));
            response.setDescription(e.getMessage());
           return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
       }
        catch(OutOfLimitNumberTransfert e) {
            response.setStatus(String.valueOf(HttpStatus.EXPECTATION_FAILED));
            response.setDescription(e.getMessage());
           return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
       }
 
 
   }

    @GetMapping("/api/wallet/{walletId}/lastNTransactions/{n}")
    public ResponseEntity<ServiceResponse> getStatement(@PathVariable ("walletId") int walletId,
                                        @PathVariable ("accountId") int accountId,
                                        @PathVariable ("n") int n) {

        ServiceResponse response = new ServiceResponse();

        try {
            List<WaletTransaction> lb = walletService.transactionWallet(walletId, n);
            response.setStatus("200");
            response.setDescription("Statement fetched successfully!!");
            response.setData(lb);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch(WalletIdDoesNotExistException e) {
            response.setStatus(String.valueOf(HttpStatus.NOT_FOUND));
            response.setDescription(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        

    }

}
