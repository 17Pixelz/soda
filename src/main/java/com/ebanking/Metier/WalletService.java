package com.ebanking.Metier;

import java.util.List;
 import com.ebanking.exception.*;
import com.ebanking.exception.CustomerAlreadyHasWalletException;
import com.ebanking.exception.CustomerDoesNotExistException; 
import com.ebanking.exception.InsuffisantSoldWalletException;
import com.ebanking.exception.WalletIdDoesNotExistException;
import com.ebanking.models.CompteCl;
import com.ebanking.models.WaletTransaction;
import com.ebanking.models.Wallet;

public interface WalletService {

    
    public Wallet createWallet(Integer customerId) throws CustomerDoesNotExistException, CustomerAlreadyHasWalletException;

    

	public Double getSoldeCompteForCurrentWallet(Integer walledId)
			throws WalletIdDoesNotExistException;
	public void retraitDeCompte(Integer walletId,double amount) throws WalletIdDoesNotExistException,
	InsuffisantSoldWalletException;
 
	public void transferVersCompte(Integer fromWalletId,Integer idBeneficiare,
			double amount,int typeFrais,boolean notif) throws WalletIdDoesNotExistException, InsuffisantSoldWalletException,OutOfLimitAmountTrnsfert
	 ,OutOfLimitNumberTransfert;
	
	 public List<WaletTransaction> transactionWallet(Integer walletId, Integer n) throws WalletIdDoesNotExistException;
	 public void virementCompteBanquaire(double amount);
		
    public void virement(int walletId,double amount)
			throws WalletIdDoesNotExistException ;
    public void servirTransfertWallet(int transfertcode,int idAgent)
    throws ExpirationDelai,TransfertEstBloqué,TransfertDoesNotExistException,TransfertEstPayé;
    
	public void retraitDeCompteAgent(int compteId,double amount)  ;

}
