package com.ebanking.dao;

import com.ebanking.models.CompteAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteAgentRepo extends JpaRepository<CompteAgent,Integer> {

}
