package com.ebanking.Metier.Imp;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebanking.Metier.WalletService;
import com.ebanking.comparator.walletTransactionSortingComparator;
import com.ebanking.constant.Constants;
import com.ebanking.dao.AgentRepository;
import com.ebanking.dao.BeneficiaireRepository;
import com.ebanking.dao.ClientRepository;
import com.ebanking.dao.CompteAgentRepo;
import com.ebanking.dao.CompteBanquireRep;
import com.ebanking.dao.CompteClRepository;
import com.ebanking.dao.WalletRepository;
import com.ebanking.dao.WalletTransactionRepository; 
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
import com.ebanking.models.Agent;
import com.ebanking.models.Beneficiaire;
import com.ebanking.models.Client;
import com.ebanking.models.CompteAgent;
import com.ebanking.models.CompteBanque;
import com.ebanking.models.CompteCl;
import com.ebanking.models.Etat;
import com.ebanking.models.TypeFrais;
import com.ebanking.models.WaletTransaction;
import com.ebanking.models.Wallet; 
@Service("walletService")
@Transactional
public class WalletServiceImpl implements WalletService{
	@Autowired
	BeneficiaireRepository  beneficiaireRepository;
	@Autowired
	CompteAgentRepo  compteAgentrep;
	
	@Autowired
	AgentRepository  agentRep;
	
	@Autowired
	CompteBanquireRep  cmptrep;
   
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    CompteClRepository accountRepository;
    @Autowired
    ClientRepository customerRepository;
    @Autowired
   WalletTransactionRepository walletTansactionRepository;

    @Override
    public Wallet createWallet(Integer customerId) throws CustomerDoesNotExistException, CustomerAlreadyHasWalletException {

        Client c = customerRepository.findById(customerId).orElse(null);
          if (c == null) {
            throw new CustomerDoesNotExistException(customerId);
        }
          CompteCl compteCl = accountRepository.findById(c.getCompte().getId()).orElse(null);
          
        if (compteCl.getWalletPorteur() != null) {
            throw new CustomerAlreadyHasWalletException(c);
        }
 
        Wallet w = new Wallet(); 
        compteCl.setWalletPorteur(w);
        accountRepository.save(compteCl);
        return  walletRepository.save(w);
    }

	@Override
	public Double getSoldeCompteForCurrentWallet(Integer walledId)
			throws WalletIdDoesNotExistException {
		 Wallet wallet = walletRepository.findById(walledId).orElse(null);

         if (wallet==null) {
            throw new WalletIdDoesNotExistException(walledId);
        }
 
        CompteCl a =  wallet.getComptInWallet(); 
       return a.getSoldeCompte();
		 
	}
@Override
public void retraitDeCompteAgent(int compteId,double amount)  
{
	CompteAgent compte=compteAgentrep.findById(compteId).orElse(null);
	compte.setSoldeCompte(compte.getSoldeCompte()-amount);
	compteAgentrep.save(compte);
}
	@Override
	public void retraitDeCompte(Integer walletId,double amount) throws WalletIdDoesNotExistException,
	InsuffisantSoldWalletException
	{
		
		 Wallet wallet = walletRepository.findById(walletId).orElse(null);
		 
		           if (wallet==null) {
		             throw new WalletIdDoesNotExistException(walletId);
		         }
		          CompteCl associateAccount =  wallet.getComptInWallet();
		           
		         if (associateAccount.getSoldeCompte()< amount) {
		             throw new InsuffisantSoldWalletException(walletId);
		         }
		 
		         double soldeActuel = associateAccount.getSoldeCompte();
		       CompteCl ac = associateAccount;
		       ac.setSoldeCompte(soldeActuel - amount);
		 
		      
		                } 
	 
 
	@Override
	public void virement(int walletId,double amount)
			throws WalletIdDoesNotExistException {

Wallet wallet = walletRepository.findById(walletId).orElse(null);

   if (wallet==null) {
   throw new WalletIdDoesNotExistException(walletId);
}
CompteCl associateAccount =  wallet.getComptInWallet();
 
double soldeActuel = associateAccount.getSoldeCompte();
CompteCl c=associateAccount;

System.out.print("pppppppppp"+c.getSoldeCompte());
 c.setSoldeCompte(soldeActuel + amount);
 
 System.out.print("eeeee"+c.getSoldeCompte());
	}

	@Override
	public void virementCompteBanquaire(double amount)
			  {

		 CompteBanque a = cmptrep.findAll().get(0);
		 System.out.println("hello"+a.getSoldeCompte());
double soldeActuel = a.getSoldeCompte();
 
 
 a.setSoldeCompte(soldeActuel + amount);
 
 
	}

	@Override
	public void transferVersCompte(Integer fromWalletId, Integer BenifId,
			double amount,int typeFrais,boolean notif) throws WalletIdDoesNotExistException, InsuffisantSoldWalletException,OutOfLimitAmountTrnsfert
	 ,OutOfLimitNumberTransfert
	{
		 Wallet fromWallet = walletRepository.findById(fromWalletId).orElse(null);
		 Beneficiaire benif=beneficiaireRepository.findById(BenifId).orElse(null);
           
       if (fromWallet == null ) {
           throw new WalletIdDoesNotExistException(fromWalletId);
       }

       CompteCl fromAssociateAccount =  fromWallet.getComptInWallet();
      if(typeFrais==1)
       amount+=Constants.MONTANT_FRAIS;
      else if(typeFrais==2)
   amount+=Constants.MONTANT_FRAIS/2;
      
      if(amount>=Constants.PLAFONT_TRANSFERT_WALLET) {
    	  throw new OutOfLimitAmountTrnsfert(fromWalletId);
      }
      if(fromAssociateAccount.getNbrTransfertWallet()<12 && fromAssociateAccount.getMontantTotalTransfert()>=Constants.PLAFONT_TRANSFERT_WALLET_ANNUEL) {
    	  throw new OutOfLimitNumberTransfert(fromWalletId);
      }
    this.retraitDeCompte(fromWalletId,amount);
     this.virementCompteBanquaire(amount);
     fromAssociateAccount.setMontantTotalTransfert(fromAssociateAccount.getMontantTotalTransfert()+amount);
     fromAssociateAccount.setNbrTransfertWallet(fromAssociateAccount.getNbrTransfertWallet()+1);
     accountRepository.save(fromAssociateAccount);
     EnregistrerTransaction(amount,notif,Constants.PLAFONT_TRANSFERT_WALLET,fromAssociateAccount, benif,Etat.A_servir,typeFrais,Constants.PLAFONT_TRANSFERT_WALLET_ANNUEL);
       
		
	}
	  private void EnregistrerTransaction(double amount,boolean notif,double plafont,CompteCl associatedAccountFrom,Beneficiaire benif,Etat etat,int typeF,double plafontAnnuel) {
	       WaletTransaction transaction = new WaletTransaction(null,null,amount,plafont,typeF,notif,plafontAnnuel,associatedAccountFrom,benif,etat,Constants.DELAI);

	         walletTansactionRepository.save(transaction);
	    }
	 
    
   

    @Override
    public List<WaletTransaction> transactionWallet(Integer walletId, Integer n) throws WalletIdDoesNotExistException
             {

        Wallet wallet = walletRepository.findById(walletId).orElse(null);
  if (wallet==null) {
            throw new WalletIdDoesNotExistException(walletId);
        }
         CompteCl associateAccount =  wallet.getComptInWallet();


        List<WaletTransaction> walletTransactions = associateAccount.getWalletTransactions();

        Collections.sort(walletTransactions, new walletTransactionSortingComparator());
     n = walletTransactions.size()>=n?n:walletTransactions.size();
        return walletTransactions.subList(0, n);

    }
    @Override
    public void servirTransfertWallet(int transfertcode,int idAgent)
    	    throws ExpirationDelai,TransfertEstBloqué, TransfertDoesNotExistException, TransfertEstPayé{
    	
    	WaletTransaction wt=walletTansactionRepository.findById(transfertcode).orElse(null);
    	if(wt==null) {
    		throw new TransfertDoesNotExistException(transfertcode);
    	}
    	if(wt.getEtat()==Etat.Bloqué) {
    		throw new TransfertEstBloqué(transfertcode);
    	}
    	if(wt.getEtat()==Etat.payé) {
    		throw new TransfertEstPayé(transfertcode);
    	}
    	 LocalDateTime now = LocalDateTime.now();  
    	 long differenceInDays = ChronoUnit.DAYS.between(now,wt.getDateT());
    	 
    	 if(differenceInDays>wt.getDelai()) {
    		 throw new ExpirationDelai(transfertcode);
    	 }
    	 Agent agent=agentRep.findById(idAgent).orElse(null);
    	 CompteAgent ca=agent.getCompteAgent();
    	 this.retraitDeCompteAgent(ca.getId(),wt.getAmount());
    	 wt.setEtat(Etat.payé);
    	 walletTansactionRepository.save(wt);
    	
    } 
}
