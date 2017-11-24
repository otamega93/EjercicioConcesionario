package com.atsistemas.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atsistemas.entities.Estado;
import com.atsistemas.entities.Pedido;

public interface PedidoService {

	public Pedido save(Pedido pedido);
	
	public List<Pedido> findAll();
	
	public Page<Pedido> findAllPages(Pageable pageable);
	
	public Pedido findById(Long id);
	
	public boolean delete(Long id);

	public Pedido receivePackage(Long id);
	
	public Pedido generatePackage(Pedido pedido);
	
}
