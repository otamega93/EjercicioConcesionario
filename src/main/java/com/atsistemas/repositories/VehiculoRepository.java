package com.atsistemas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.atsistemas.entities.Vehiculo;

public interface VehiculoRepository extends PagingAndSortingRepository<Vehiculo, Long> {

}
