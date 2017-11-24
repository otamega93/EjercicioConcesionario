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

import com.atsistemas.entities.Pedido;
import com.atsistemas.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoRestController {

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findAll(Pageable pageable) {

		Page<Pedido> pedidos = pedidoService.findAllPages(pageable);
		return new ResponseEntity<Page<Pedido>>(pedidos, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Pedido> create(@RequestBody @Valid Pedido pedido) {

		Pedido pedidoGenerated = pedidoService.generatePackage(pedido);
		if (null == pedidoGenerated) {
			return new ResponseEntity<Pedido>(HttpStatus.I_AM_A_TEAPOT);
		} else
			return new ResponseEntity<Pedido>(pedidoGenerated, HttpStatus.CREATED);

	}
}
