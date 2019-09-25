package com.rappi.apuestaservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString



@Document(collection="Apuesta")
public class Apuesta {
	 
	
	@Id
	private  String id;	
	private float valor_apostado;
	private Date fechaApuesta;
	private String estado_pago;
	private String idApostador;
	private String idTarifa;
	private Tarifa tarifa;
	private String tipoTarifa;
	private Apostador apostador;
	

	

	
	
	
	
	
	
	
	
	
	
	

}
