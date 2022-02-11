package com.ebanking.Metier.Imp;


import com.ebanking.dao.TransfertRepo;
import com.ebanking.exception.ClientNotFound;
import com.ebanking.exception.CompteClNotFound;
import com.ebanking.Metier.ICompteCl;
import com.ebanking.dao.ClientRepository;
import com.ebanking.dao.CompteClRepository;
import com.ebanking.models.Client;
import com.ebanking.models.CompteCl;
import com.ebanking.models.TransfertparClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteClService implements ICompteCl {

    @Autowired
    CompteClRepository compteClRepository ;
    @Autowired
    ClientRepository clientRepository;




    @Override
    public List<CompteCl> GetAllcomptecl() {
        return compteClRepository.findAll();
    }

    @Override
    public CompteCl GetComptecl(int id) throws CompteClNotFound {
        CompteCl compteCl=compteClRepository.findById(id).orElse(null);
        if(compteCl==null ) throw new CompteClNotFound();
        else{
            return compteCl;
        }
    }

    @Override
    public CompteCl AddCompteCl(CompteCl compteCl) {
        return  compteClRepository.save(compteCl);
    }

    @Override
    public List<TransfertparClient> GetTransferts(int idcomptecl) throws CompteClNotFound {
        CompteCl compteCl= compteClRepository.findById(idcomptecl).orElse(null);
        if(compteCl==null) {
            throw new CompteClNotFound();
        }
        else{
            return compteCl.getTransfertparClient();
        }
    }

    @Override
    public Client UpdateClient(Client client) throws ClientNotFound {
        Client existingclient=clientRepository.findById(client.getIduser()).orElse(null);
        if(client==null) {
            throw new ClientNotFound();

        }else {
            existingclient.setNomclient(client.getNomclient());
            existingclient.setPrenomClient(client.getPrenomClient());
            existingclient.setKyc(client.getKyc());
            return clientRepository.save(existingclient);
        }

    }

    @Override
    public CompteCl UpdateCompteCl(CompteCl compteCl) throws CompteClNotFound {
        CompteCl existingcomptecl=compteClRepository.findById(compteCl.getId()).orElse(null);
        if(existingcomptecl==null   ) throw new CompteClNotFound();
        else{
            existingcomptecl.setSoldeCompte(compteCl.getSoldeCompte());
            existingcomptecl.setDate_création(compteCl.getDate_création());
            existingcomptecl.setNumeroCompte(compteCl.getNumeroCompte());
            existingcomptecl.setDate_premierTransfert(compteCl.getDate_premierTransfert());
            existingcomptecl.setSomme_transferts_Année(compteCl.getSomme_transferts_Année());
            existingcomptecl.setWalletPorteur(compteCl.getWalletPorteur());
            return compteClRepository.save(existingcomptecl);
        }
    }

    @Override
    public String DelecteCompteCl(int idcomptecl) throws CompteClNotFound {
        CompteCl compteCl=compteClRepository.findById(idcomptecl).orElse(null);
        if(compteCl==null) throw new CompteClNotFound();
        else{
            compteClRepository.delete(compteCl);
            return "Client Account with id :" + idcomptecl +" is deleted ." ;
        }
    }

    @Override
    public List<Client> GetAllClient() {
        return clientRepository.findAll();
    }

    @Override
    public Client GetClient(int id) throws ClientNotFound {
        Client client=clientRepository.findById(id).orElse(null);
        if(client==null ) throw new ClientNotFound();
        else{
            return client;
        }
    }

    @Override
    public Client AddClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public String DeleteClient(int idclient) throws ClientNotFound {
       Client client= clientRepository.findById(idclient).orElse(null);
       if(client==null) throw new ClientNotFound();
       else{
           clientRepository.delete(client);
           return "Client with id : "+ idclient +" is deleted ." ;
       }

    }


}
