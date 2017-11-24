package com.atsistemas.restcontrollers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.entities.Vehiculo;
import com.atsistemas.services.VehiculoService;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoRestController {

	@Autowired
	private VehiculoService vehiculoService;


	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<Vehiculo>> findAll(Pageable pageable) {

		Page<Vehiculo> vehiculos = vehiculoService.findAllPages(pageable);
		return new ResponseEntity<Page<Vehiculo>>(vehiculos, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Vehiculo> create(@RequestBody @Valid Vehiculo vehiculo) {

		return new ResponseEntity<Vehiculo>(vehiculoService.save(vehiculo), HttpStatus.CREATED);

	}
}
