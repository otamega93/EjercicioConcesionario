package com.atsistemas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.atsistemas.entities.Pedido;

public interface PedidoRepository extends PagingAndSortingRepository<Pedido, Long> {

	public void deleteById(Long id);
	
	public Pedido findOneById(Long id); 
}
