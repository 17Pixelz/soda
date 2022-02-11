package com.ebanking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ebanking.models.Agent;
import com.ebanking.models.CompteBanque;
@RepositoryRestResource
public interface CompteBanquireRep extends JpaRepository<CompteBanque, Integer> {

}
