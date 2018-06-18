package com.atsistemas.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.entities.Vehiculo;
import com.atsistemas.repositories.VehiculoRepository;
import com.atsistemas.services.VehiculoService;
import com.google.common.collect.Lists;

@Service
@Transactional(readOnly=true)
public class VehiculoServiceImpl implements VehiculoService {
	
	@Autowired
	private VehiculoRepository vehiculoRepository;

	
	@Transactional(readOnly=false)
	@Override
	public Vehiculo save(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public List<Vehiculo> findAll() {
		
		List<Vehiculo> vehiculos = new ArrayList<>();
		vehiculos = Lists.newArrayList(vehiculoRepository.findAll());
		return vehiculos;
	}

	@Override
	public Page<Vehiculo> findAllPages(Pageable pageable) {
		
		List<Vehiculo> vehiculos = new ArrayList<>();
		vehiculos = Lists.newArrayList(vehiculoRepository.findAll());
		PageImpl<Vehiculo> vehiculospages = new PageImpl<>(vehiculos, pageable, vehiculoRepository.count());
		return vehiculospages;
	}

	@Override
	public Vehiculo findById(Long id) {
		return vehiculoRepository.findOneById(id);
	}

	@Transactional(readOnly=false)
	@Override
	public boolean delete(Long id) {
		vehiculoRepository.deleteById(id);
		return true;
	}

}
