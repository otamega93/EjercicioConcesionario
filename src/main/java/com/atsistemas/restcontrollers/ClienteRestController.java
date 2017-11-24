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

import com.atsistemas.entities.Cliente;
import com.atsistemas.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {

		Page<Cliente> clientes = clienteService.findAllPages(pageable);
		return new ResponseEntity<Page<Cliente>>(clientes, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Cliente> create(@RequestBody @Valid Cliente cliente) {

		return new ResponseEntity<Cliente>(clienteService.save(cliente), HttpStatus.CREATED);

	}
}
