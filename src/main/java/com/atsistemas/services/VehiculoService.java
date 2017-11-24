package com.atsistemas.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atsistemas.entities.Vehiculo;

public interface VehiculoService {

	public Vehiculo save(Vehiculo vehiculo);

	public List<Vehiculo> findAll();

	public Page<Vehiculo> findAllPages(Pageable pageable);

	public Vehiculo findById(Long id);

	public boolean delete(Long id);
}
