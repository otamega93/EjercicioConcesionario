package com.atsistemas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.atsistemas.entities.Estado;

public interface EstadoRepository extends PagingAndSortingRepository<Estado, Long> {

	public Estado findOneById(Long id);
	
}
