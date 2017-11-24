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

import com.atsistemas.entities.Estado;
import com.atsistemas.repositories.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoRestController {

	//Not a good practice, but lazy ah
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<Page<Estado>> findAll(Pageable pageable) {

		Page<Estado> estados = estadoRepository.findAll(pageable);
		return new ResponseEntity<Page<Estado>>(estados, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Estado> create(@RequestBody @Valid Estado estado) {

		return new ResponseEntity<Estado>(estadoRepository.save(estado), HttpStatus.CREATED);

	}
}
