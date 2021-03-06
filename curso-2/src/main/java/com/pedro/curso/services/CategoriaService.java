package com.pedro.curso.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedro.curso.domain.Categoria;
import com.pedro.curso.repositories.CategoriaRepository;
import com.pedro.curso.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo; //Autowired: dependência é automaticamente instanciada. Injeção de dependência.
	
	public Categoria buscar(Integer id) {
		//O serviço deve chamar um objeto da camada de acesso a dados (Repository)
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
		
}
