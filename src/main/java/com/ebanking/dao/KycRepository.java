package com.ebanking.dao;

import com.ebanking.models.KYC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KycRepository extends JpaRepository<KYC,Integer> {
    KYC findByGSM(String gsm);
    KYC findByNumPiece(String cin);
}
