package com.ebanking.dao;

import com.ebanking.models.CompteAgent;
import com.ebanking.models.CompteCl;
import com.ebanking.models.Transfert;

import com.ebanking.models.TransfertparClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransfertRepo extends JpaRepository<Transfert, Integer> {
    List<Transfert> findTransfertByTypeTransfert(String typeTransfert);
      List<Transfert> findTransfertByTypeTransfertAndCompteAgent(String typeTransfert, CompteAgent compteAgent);
   List<Transfert> findTransfertByTypeTransfertAndCompteCl(String typeTransfert, CompteCl compteCl);

}
