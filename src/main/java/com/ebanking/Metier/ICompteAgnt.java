package com.ebanking.Metier;

import com.ebanking.exception.CompteAgentNotFound;
import com.ebanking.models.CompteAgent;

import java.util.List;

public interface ICompteAgnt {
        public List<CompteAgent> GetAllcompteagent();
        public CompteAgent GetCompteagent(int id) throws CompteAgentNotFound;
        public CompteAgent AddCompteagent(CompteAgent compteAgent);
        public CompteAgent UpdateCompteagent(CompteAgent compteAgent) throws CompteAgentNotFound;
        public String DeleteCompteagent(int id )throws CompteAgentNotFound;

}
