
package com.rappi.apuestaservice.repository;

import java.awt.List;
import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import com.rappi.apuestaservice.model.Apuesta;

public interface ApuestaRepository extends MongoRepository<Apuesta, String> {
	
	 
	
	Apuesta findApuestaById(String ApuestaId);
	ArrayList<Apuesta> findApuestaByIdApostador(String ApostadorId);
	ArrayList<Apuesta> findApuestaByIdTarifa(String TarifaId);
	

}
