package com.atsistemas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.atsistemas.entities.Comercial;

public interface ComercialRepository extends PagingAndSortingRepository<Comercial, Long> {

	public Comercial findOneById(Long id);
	
	public void deleteById(Long id);
}
