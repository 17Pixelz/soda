package com.ebanking.dao;

import com.ebanking.models.CompteCl;
import com.ebanking.models.TransfertparClient;
import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import com.ebanking.models.Agent;
import com.ebanking.models.Client;
@Repository 
public interface ClientRepository extends JpaRepository<Client, Integer> {
   

}