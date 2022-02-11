package com.ebanking.dao;

import com.ebanking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ebanking.models.Agent;

import java.util.Optional;

@RepositoryRestResource
public interface AgentRepository extends JpaRepository<Agent,Integer> {
   Optional<Agent> findByUser(User user);
}
