package com.ebanking.Metier;

import com.ebanking.dto.TransfertRequest;
import com.ebanking.exception.*;
import com.ebanking.models.*;

import java.util.List;


public interface ITransfertMetier {
	 CompteCl findByTelnumber(String telnumber) throws ClientNotFound, KycNotFound;
	 Client findbyIdentité(String carteidentité) throws ClientNotFound, KycNotFound;
	 Beneficiaire saveBenificiare(String nom,String prenom,String phone);
	 void transfer_CL(int compteClid, int beneficiaireid, Double montant,
							Double fraisTransfert, int option, int hours,String motif,boolean notification)
			throws OutOfLimitAmountTransfert, OutOfLimitAmountTrnsfertAnnuel;
	 void transfer_CA(TransfertRequest request) throws AgentNotFound, ClientNotFound, KycNotFound, UserNotFound;


	//Les transferts par un compte client ou compte agent
	 List<Transfert> GetAlltransfertbyclient(int id);
	 List<Transfert> GetAlltransfertbyagent(int id);
	//Tous les transferts selon le compte utilisé pour payer
	List<Transfert> GetAlltransfertbyagents();
	List<Transfert> GetAlltransfertbyclients();

	Transfert Getransfertparclient(int id) throws TransactionClNotFound;
	Transfert Gettransfertparagent(int id) throws TransactionAgentNotFound;

	List<Beneficiaire> GetBenificairesparClient(CompteCl compteCl);
	//Extourner un transfert
	void ReverseTransfert(int transfid) throws TransfertDoesNotExistException;
	//Restituer un transfert
	void ReturnTransfer(int transfid) throws TransfertDoesNotExistException;
	//Bloquer un transfert
	void BlockTransfert(int transfid) throws TransfertDoesNotExistException;
	//Débloquer un transfert
	void UnblockTransfert(int transfid) throws TransfertDoesNotExistException;
	//Transfert en déshérence
	void DéshérenceTransfert(int transfid) throws TransfertDoesNotExistException;

}
