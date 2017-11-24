package com.atsistemas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.atsistemas.entities.Cliente;

public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>{

	
}
