package com.ebanking.dao;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ebanking.models.User;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Integer> {
	 	Optional<User> findByUsername(String username);
        Boolean existsByUsername(String username);
 
}
