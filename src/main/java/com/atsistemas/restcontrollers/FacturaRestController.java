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
import com.atsistemas.entities.Factura;
import com.atsistemas.entities.Pedido;
import com.atsistemas.services.FacturaService;

@RestController
@RequestMapping("/facturas")
public class FacturaRestController {

	@Autowired
	private FacturaService facturaService;
	
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public ResponseEntity<Page<Factura>> findAll(Pageable pageable) {

		Page<Factura> facturas = facturaService.findAllPages(pageable);
		return new ResponseEntity<Page<Factura>>(facturas, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Factura> create(@RequestBody @Valid Factura factura) {

		return new ResponseEntity<Factura>(facturaService.GenerateInvoice(factura), HttpStatus.CREATED);

	}
}
