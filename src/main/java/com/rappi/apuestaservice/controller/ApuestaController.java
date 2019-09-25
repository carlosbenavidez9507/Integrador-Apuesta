package com.rappi.apuestaservice.controller;



import com.rappi.apuestaservice.model.Apostador;
import com.rappi.apuestaservice.model.Apuesta;
import com.rappi.apuestaservice.model.Operacion;
import com.rappi.apuestaservice.model.Tarifa;
import com.rappi.apuestaservice.repository.ApuestaRepository;
import com.sun.javafx.collections.MappingChange.Map;

import jdk.nashorn.internal.parser.JSONParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import com.google.gson.JsonObject;
import com.mongodb.util.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Http.HttpMethod;

@RestController
@RequestMapping("/")
public class ApuestaController {
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	 @Autowired
	 private ApuestaRepository repository;
	 
	 
	
	 
	 @GetMapping(value = "/apuesta")
		public Iterable<Apuesta> all (){
			return repository.findAll();
		}
	 
	 @GetMapping(value = "/apuesta/{apuestaId}")
		public Apuesta findByApuestatId (@PathVariable String apuestaId){
		 
		     Apuesta apuesta=repository.findApuestaById(apuestaId);
		     //Tarifa tarifa= (Tarifa) restTemplate.getForObject("http://tarifa-service/tarifa/{tarifaId}", Object.class,apuesta.getTarifa());
             //apuesta.setTarifa(tarifa);
		     return apuesta;
		     
		}
	 
	 
	 
	 @PostMapping(value = "/apuesta")
		public Apuesta save (@RequestBody Apuesta apuesta){
			return repository.save(apuesta);
		}
	 
	
	 
	 @PutMapping(value = "/apuesta")
		public Apuesta update (@RequestBody Apuesta apuesta){
			return repository.save(apuesta);
		}
	 
	 @DeleteMapping(value = "/apuesta")
		public void delete (@RequestBody Apuesta apuesta){
			repository.delete(apuesta);
		}
	 
	 
	 @GetMapping(value = "/apuesta/apostador/{apostadorId}")
		public ArrayList<Apuesta> findApuestaByApostador (@PathVariable String apostadorId) throws JSONException{
		 
		 ArrayList<Apuesta> apuesta=repository.findApuestaByIdApostador(apostadorId);
		 //return apuesta;
		 
		 for(int j=0;j<apuesta.size();j++)
		 {      
			    Apuesta bet=apuesta.get(j);
			 
			    String respuesta=restTemplate.getForObject("http://tarifa-service/tarifa/{tarifaId}", String.class,apuesta.get(j).getIdTarifa());
				int i = respuesta.indexOf("{");
				Apostador apostador=new Apostador();
				//JSONObject jsonFile = new JSONObject(respuesta);
			//JSONObject jsonObj = new JSONObject(respuesta);
				respuesta = respuesta.substring(i);
				JSONObject jsonFile = new JSONObject(respuesta);
				//apostador.setApuesta(apuestaHecha);
				//apostador.setDetalle(jsonFile);
				JSONObject tmp=jsonFile.getJSONObject("partido");
				apostador.setDetalle(jsonFile.get("tarifa"));
				JSONObject equipoLocal=tmp.getJSONObject("equipoLocal");
				JSONObject equipoVisitante=tmp.getJSONObject("equipoVisitante");
				apostador.setEquipoLocal(equipoLocal.get("nombreEquipo"));
				apostador.setEquipoVisitante(equipoVisitante.get("nombreEquipo"));
				//apuesta.setApostador(apostador);
				bet.setApostador(apostador);
				apuesta.set(j,bet);
				
		 }
		 
		 
		 return apuesta;
		}
	 
	 
	 
	 
	 
	 
	 
	 @GetMapping(value = "/apuesta/{apuestaId}/apostador")
		public Apuesta findApostadoresbyApuesta (@PathVariable String apuestaId) throws JSONException{
		
		 Apuesta apuesta=repository.findApuestaById(apuestaId);		
		 Apostador apostador = restTemplate.getForObject("http://apostador-service/apostador/{apostadorId}", Apostador.class,apuesta.getIdApostador());
        
		 //HttpHeaders headers = new HttpHeaders();
		 String respuesta=restTemplate.getForObject("http://tarifa-service/tarifa/{tarifaId}", String.class,apuesta.getTarifa());
         //ArrayList<Apuesta> apuestaHecha=new ArrayList<>();
		 //apuestaHecha.add(apuesta);
		//apostador.setDetalle(respuesta);
		
		//JsonObject jsonObject = ((MediaType) new JsonParser()).parse(respuesta).getAsJsonObject();
		//JsonObject jsonObject = new JsonParser().parse(respuesta).getAsJsonObject();
		int i = respuesta.indexOf("{");
		//JSONObject jsonFile = new JSONObject(respuesta);
	//JSONObject jsonObj = new JSONObject(respuesta);
		respuesta = respuesta.substring(i);
		JSONObject jsonFile = new JSONObject(respuesta);
		//apostador.setApuesta(apuestaHecha);
		//apostador.setDetalle(jsonFile);
		JSONObject tmp=jsonFile.getJSONObject("partido");
		apostador.setDetalle(jsonFile.get("tarifa"));
		JSONObject equipoLocal=tmp.getJSONObject("equipoLocal");
		JSONObject equipoVisitante=tmp.getJSONObject("equipoVisitante");
		apostador.setEquipoLocal(equipoLocal.get("nombreEquipo"));
		apostador.setEquipoVisitante(equipoVisitante.get("nombreEquipo"));
		apuesta.setApostador(apostador);
		 return apuesta;
		}
	 @GetMapping(value="/apuesta/{apuestaId}/tarifa")
	 public Apuesta findTarifaApuesta(@PathVariable String apuestaId)
	 {
		 Apuesta apuesta=repository.findApuestaById(apuestaId);
		 return null;
	 }
	
	 
	
	
	
	
	
	

}
