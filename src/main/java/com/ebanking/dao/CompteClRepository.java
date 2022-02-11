package com.ebanking.dao;

import com.ebanking.models.CompteCl;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteClRepository extends JpaRepository<CompteCl, Integer> {


}
