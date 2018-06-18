package com.atsistemas.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.entities.Account;
import com.atsistemas.entities.Cliente;
import com.atsistemas.entities.Comercial;
import com.atsistemas.entities.Estado;
import com.atsistemas.entities.Factura;
import com.atsistemas.entities.Pedido;
import com.atsistemas.entities.Vehiculo;
import com.atsistemas.repositories.ClienteRepository;
import com.atsistemas.repositories.ComercialRepository;
import com.atsistemas.repositories.EstadoRepository;
import com.atsistemas.repositories.FacturaRepository;
import com.atsistemas.repositories.PedidoRepository;
import com.atsistemas.repositories.VehiculoRepository;
import com.atsistemas.services.FacturaService;
import com.atsistemas.services.PedidoService;
import com.google.common.collect.Lists;

@Service
@Transactional(readOnly = true)
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private FacturaService facturaService;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ComercialRepository comercialRepository;

	@Autowired
	private FacturaRepository facturaRepository;

	@Autowired
	private VehiculoRepository vehiculoRepository;
	

	@Transactional(readOnly = false)
	@Override
	public Pedido generatePackage(Pedido pedido) {
		// Getting all the objects related to pedido and setting them in order
		// to map
		Cliente cliente = clienteRepository.findOneById(pedido.getCliente().getId());
		Comercial comercial = comercialRepository.findOneById(pedido.getComercial().getId());
		Estado estado = estadoRepository.findOneById(pedido.getEstado().getId());
		
		// Obtenemos los coches	
		List<Vehiculo> vehiculos = pedido.getVehiculos();
		
		List<Vehiculo> vehiculosConsultados = new ArrayList<>();
		
		if (null != vehiculos && !vehiculos.isEmpty()) {

			for (Vehiculo vehiculo : vehiculos) {

				vehiculosConsultados.add(vehiculoRepository.findOneById(vehiculo.getId()));
			}
		}

		// Now, the mapping
		if (null != cliente) {
			cliente.getPedidos().add(pedido);
			//clienteRepository.save(cliente);
		}
		else 
			return null;

		if (null != comercial) {
			comercial.getPedidos().add(pedido);
			//comercialRepository.save(comercial);
		}
		else
			return null;

		// set the current package's status
		if (null != estado) {
			pedido.setEstado(estado);
		}
		else
			return null;
		
		// Set date and persisting
		pedido.setFecha(LocalDateTime.now());
		pedido.setCliente(cliente);
		pedido.setComercial(comercial);
		pedido.setVehiculos(vehiculosConsultados);

		return pedidoRepository.save(pedido);
	}

	@Override
	public List<Pedido> findAll() {
		
		List<Pedido> pedidos = new ArrayList<>();
		pedidos = Lists.newArrayList(pedidoRepository.findAll());
		return pedidos;
	}

	@Override
	public Page<Pedido> findAllPages(Pageable pageable) {
		
		List<Pedido> pedidos = new ArrayList<>();
		pedidos = Lists.newArrayList(pedidoRepository.findAll(pageable));
		PageImpl<Pedido> pedidosPages= new PageImpl<>(pedidos, pageable, pedidoRepository.count());
		return pedidosPages;
	}

	@Override
	public Pedido findById(Long id) {
		return pedidoRepository.findOneById(id);
	}

	@Transactional(readOnly = false)
	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Transactional(readOnly = false)
	@Override
	public Pedido receivePackage(Long id) {
		Pedido pedido = pedidoRepository.findOneById(id);

		if (pedido.getId() != null) {
			pedido.setId(pedido.getId());
			pedido.setEstado(estadoRepository.findOneById(1L));

			// method to generate the invoice
			//Factura factura = facturaService.GenerateInvoice(factura);
			//pedido.setFactura(factura);

			return pedidoRepository.save(pedido);
		}

		return null;
	}

	@Transactional(readOnly = false)
	@Override
	public Pedido save(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}

}
