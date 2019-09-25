package com.rappi.apuestaservice.model;

import java.util.ArrayList;
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

@Document(collection="Apostador")
public class Apostador {
	
	@Id
	private  String id;	
	private String nombres;
	private String apellidos;
	private String correo;
	private String fecha_nacimiento;
	private ArrayList<Apuesta> apuesta;
	private Object detalle;
	private Object equipoLocal;
	private Object equipoVisitante;
	
	
	
	
	

}
