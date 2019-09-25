package com.rappi.apuestaservice.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString



public class Operacion {
	
	@Id
	private String id;
	private Apostador apostador;
	private Tarifa tarifa;
	
	

}
