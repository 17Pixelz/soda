package com.ebanking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ebanking.models.Wallet;
@RepositoryRestResource
public interface WalletRepository extends JpaRepository<Wallet,Integer>{
	  
}
