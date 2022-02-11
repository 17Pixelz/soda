package com.ebanking.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("agent")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAgent;
    @OneToOne 
    @JoinColumn(name="user_id")
   private User user;

    @OneToOne(mappedBy = "agent")
    @JoinColumn(name="compteagent")
    private CompteAgent compteAgent;



    @OneToMany(mappedBy = "agent", fetch = FetchType.LAZY)
    private Collection<Guichet> guichets;

    @ManyToOne
    private Admin admin;

    public Agent(User user) {

    }
}