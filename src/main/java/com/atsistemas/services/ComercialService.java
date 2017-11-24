package com.atsistemas.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atsistemas.entities.Comercial;

public interface ComercialService {

	public Comercial save(Comercial comercial);

	public List<Comercial> findAll();

	public Page<Comercial> findAllPages(Pageable pageable);

	public Comercial findById(Long id);

	public boolean delete(Long id);
}
