package com.pedro.curso.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pedro.curso.domain.Cliente;
import com.pedro.curso.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	
	@RequestMapping(value = "/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		//Retorna um ResponseEntity: Armazena várias informações HTTP para um serviço rest. "?" pode ser qualquer tipo (pode encontrar ou não)
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
