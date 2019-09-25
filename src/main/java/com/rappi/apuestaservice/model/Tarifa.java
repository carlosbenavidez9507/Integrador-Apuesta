package com.rappi.apuestaservice.model;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString


public class Tarifa{
	
	@Id
	private String id;
	private float tarifa_actual;	
	private ArrayList<Apuesta> apuesta;
	private String tarifa;
	
}
