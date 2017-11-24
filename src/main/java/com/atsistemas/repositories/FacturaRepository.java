package com.atsistemas.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.atsistemas.entities.Factura;

public interface FacturaRepository extends PagingAndSortingRepository<Factura, Long> {

}
