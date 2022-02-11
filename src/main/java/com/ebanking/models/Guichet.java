package com.ebanking.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guichet implements Serializable {

    @Id
    @GeneratedValue
    private Long numguichet;

	@ManyToOne
	private Agent agent;
}