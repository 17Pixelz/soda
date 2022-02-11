package com.ebanking.Metier.Imp;

import com.ebanking.exception.CompteAgentNotFound;
import com.ebanking.Metier.ICompteAgnt;
import com.ebanking.dao.CompteAgentRepo;
import com.ebanking.models.CompteAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteAgentService implements ICompteAgnt {

    @Autowired
    private CompteAgentRepo compteAgentRepo;


    @Override
    public List<CompteAgent> GetAllcompteagent() {
        return compteAgentRepo.findAll();
    }

    @Override
    public CompteAgent GetCompteagent(int id) throws CompteAgentNotFound {
        CompteAgent compteAgent=compteAgentRepo.findById(id).orElse(null);
        if(compteAgent==null) throw new CompteAgentNotFound();
        else{
            return compteAgent;
        }
    }

    @Override
    public CompteAgent AddCompteagent(CompteAgent compteAgent) {
        return compteAgentRepo.save(compteAgent);
    }

    @Override
    public CompteAgent UpdateCompteagent(CompteAgent compteAgent) throws CompteAgentNotFound {
        CompteAgent exisistingcompteagent=compteAgentRepo.findById(compteAgent.getId()).orElse(null);
        if(exisistingcompteagent==null) throw new CompteAgentNotFound();
        else{
            exisistingcompteagent.setNumeroCompte(compteAgent.getNumeroCompte());
            exisistingcompteagent.setSoldeCompte(compteAgent.getSoldeCompte());
            return compteAgentRepo.save(exisistingcompteagent);
        }
    }

    @Override
    public String DeleteCompteagent(int id) throws CompteAgentNotFound {
        CompteAgent compteAgent=compteAgentRepo.findById(id).orElse(null);
        if (compteAgent==null) throw new CompteAgentNotFound();
        else{
            compteAgentRepo.delete(compteAgent);
            return "Agent account wiht id :" + id + " is deleted .";
        }
    }
}
