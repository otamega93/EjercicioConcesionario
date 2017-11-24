package com.atsistemas.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atsistemas.entities.Cliente;

public interface ClienteService {

	public Cliente save(Cliente cliente);
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAllPages(Pageable pageable);
	
	public Cliente findById(Long id);
	
	public boolean delete(Long id);
}
