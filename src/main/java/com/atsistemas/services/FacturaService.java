package com.atsistemas.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atsistemas.entities.Estado;
import com.atsistemas.entities.Factura;
import com.atsistemas.entities.Pedido;

public interface FacturaService {

	public Factura GenerateInvoice(Factura factura);
	
	public Factura save(Factura factura);
	
	public List<Factura> findAll();
	
	public Page<Factura> findAllPages(Pageable pageable);
	
	public Factura findById(Long id);
	
	public boolean delete(Long id);
	
}
