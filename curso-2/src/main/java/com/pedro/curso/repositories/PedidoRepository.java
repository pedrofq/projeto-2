package com.pedro.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pedro.curso.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	//JpaRepository acessa dados do tipo Categoria e o atributo identificador é o id (Integer)
	//Camada de acesso.
}
