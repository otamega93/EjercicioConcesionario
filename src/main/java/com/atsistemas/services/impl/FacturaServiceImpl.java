package com.atsistemas.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.entities.Estado;
import com.atsistemas.entities.Factura;
import com.atsistemas.entities.Pedido;
import com.atsistemas.repositories.EstadoRepository;
import com.atsistemas.repositories.FacturaRepository;
import com.atsistemas.repositories.PedidoRepository;
import com.atsistemas.services.FacturaService;
import com.atsistemas.services.PedidoService;

@Service
@Transactional(readOnly=true)
public class FacturaServiceImpl implements FacturaService {

	@Autowired
	private FacturaRepository facturaRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private PedidoService pedidoService;
	
	
	@Transactional(readOnly=false)
	@Override
	public Factura GenerateInvoice(Factura factura) {
		
		factura.setFecha(LocalDateTime.now());
		Pedido pedido = pedidoService.findById(factura.getPedido().getId());
		
		factura.setPedido(pedido);
		//Seems like it persist the factura into the pedido all magically. If necessary:
		//pedido.setFactura(factura); 
		System.out.println("GET FACTURA:" + pedido.getFactura());
		factura.setTotal(400.6);
		pedidoService.save(pedido);
		
		return facturaRepository.save(factura);
	}

	@Transactional(readOnly=false)
	@Override
	public Factura save(Factura factura) {
		return facturaRepository.save(factura);
	}

	@Override
	public List<Factura> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Factura> findAllPages(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly=false)
	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
