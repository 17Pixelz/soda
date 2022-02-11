package com.ebanking.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class CompteAgent {
  @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int numeroCompte;
    private Double soldeCompte; 
     
    
    @OneToOne
	@JoinColumn(name="agent_id")
    private Agent agent;
    
}
