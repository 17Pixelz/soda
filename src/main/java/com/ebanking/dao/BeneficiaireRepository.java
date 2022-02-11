package com.ebanking.dao;

import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ebanking.models.Beneficiaire;
import com.ebanking.models.Client;

import java.util.List;

@Repository
public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Integer> {
        Beneficiaire findBeneficiaireByNom(String name);
//        @Query("SELECT b FROM Beneficiaire b WHERE b.nom=:nom and b.prenom=:prenom  ")
//        Beneficiaire fetchBenificiares(@Param("nom")String nom,@Param("prenom") String prenom);
        Beneficiaire findFirstByNomAndPrenom(String nomn,String prenom);

}