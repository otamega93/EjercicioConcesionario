package com.atsistemas.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.entities.Cliente;
import com.atsistemas.entities.Comercial;
import com.atsistemas.repositories.ClienteRepository;
import com.atsistemas.repositories.ComercialRepository;
import com.atsistemas.services.ClienteService;
import com.google.common.collect.Lists;

@Service
@Transactional(readOnly=true)
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComercialRepository comercialRepository;

	
	@Transactional(readOnly=false)
	@Override
	public Cliente save(Cliente cliente) {
		
		//Mapping the relationship between clientes and comerciales
		Comercial comercial = comercialRepository.findOne(cliente.getComercial().getId());
		
		//Persisting
		comercial.getClientes().add(cliente);
		//comercialRepository.save(comercial);
		cliente.setComercial(comercial);
		Cliente persistedCliente = clienteRepository.save(cliente);
		
		return persistedCliente;
	}

	@Override
	public List<Cliente> findAll() {
		
		List<Cliente> clientes= new ArrayList<>();
		clientes = Lists.newArrayList(clienteRepository.findAll());
		return clientes;
	}

	@Override
	public Page<Cliente> findAllPages(Pageable pageable) {
		
		List<Cliente> clientes= new ArrayList<>();
		clientes = Lists.newArrayList(clienteRepository.findAll(pageable));
		PageImpl<Cliente> clientespages = new PageImpl<Cliente>(clientes, pageable, clienteRepository.count());
		return clientespages;
	}
	
	@Override
	public Cliente findById(Long id) {
		return clienteRepository.findOne(id);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean delete(Long id) {
		clienteRepository.delete(id);
		return true;
	}

}
