package com.ebanking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ebanking.models.Guichet;

@RepositoryRestResource
public interface GuichetRpository extends JpaRepository<Guichet,Long> {

}
