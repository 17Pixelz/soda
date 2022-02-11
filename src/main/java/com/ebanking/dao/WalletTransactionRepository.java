package com.ebanking.dao;
import com.ebanking.models.*;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;
@RepositoryRestResource
public interface WalletTransactionRepository extends JpaRepository<WaletTransaction, Integer> {
	// @Query("SELECT t FROM WaletTransaction t WHERE t.transactionId=:transactionId")
	  //  Iterable<WaletTransaction> findBankTransactionById(@Param("transactionId") Integer transactionId);
  
	List<WaletTransaction> findByAmount(@Param(value = "amount") double amount);
}
