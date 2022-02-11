package com.ebanking.dao;

import com.ebanking.models.ClientProspect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientProspectsRepo extends JpaRepository<ClientProspect,Long> {

    ClientProspect findByCarteidentité(String carteidentité);
}
