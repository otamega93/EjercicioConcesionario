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

import com.atsistemas.entities.Comercial;
import com.atsistemas.services.ComercialService;

@RestController
@RequestMapping("/comerciales")
public class ComercialRestController {

	@Autowired
	private ComercialService comercialService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<Page<Comercial>> findAll(Pageable pageable) {

		Page<Comercial> comerciales = comercialService.findAllPages(pageable);
		return new ResponseEntity<Page<Comercial>>(comerciales, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Comercial> create(@RequestBody @Valid Comercial comercial) {

		return new ResponseEntity<Comercial>(comercialService.save(comercial), HttpStatus.CREATED);

	}
}
