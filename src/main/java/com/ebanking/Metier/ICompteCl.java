package com.ebanking.Metier;

import com.ebanking.exception.ClientNotFound;
import com.ebanking.exception.CompteClNotFound;
import com.ebanking.models.Client;
import com.ebanking.models.CompteCl;
import com.ebanking.models.TransfertparClient;

import java.util.List;

public interface ICompteCl {
    public List<CompteCl> GetAllcomptecl() ;
    public CompteCl GetComptecl(int id) throws CompteClNotFound;
    public CompteCl AddCompteCl(CompteCl compteCl) ;
    public CompteCl UpdateCompteCl(CompteCl compteCl) throws CompteClNotFound;
    public String DelecteCompteCl(int idcomptecl) throws CompteClNotFound;


    public List<Client> GetAllClient();
    public Client GetClient(int id) throws ClientNotFound;
    public Client AddClient(Client client);
    public Client UpdateClient(Client client) throws ClientNotFound;
    public String DeleteClient(int idclient) throws ClientNotFound;

    public List<TransfertparClient> GetTransferts(int idcomptecl) throws CompteClNotFound ;

}
