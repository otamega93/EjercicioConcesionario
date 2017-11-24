package com.atsistemas.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@JsonIgnoreProperties({"pedidos","comercial"})
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "comercial_id")
	@JsonIgnoreProperties({"pedidos","clientes"})
	private Comercial comercial;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "pedido_vehiculo", joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "vehiculo_id"))
	@JsonIgnoreProperties("pedidos")
	private List<Vehiculo> vehiculos = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	@OneToOne(mappedBy="pedido")
	@JsonIgnoreProperties("pedido")
	private Factura factura;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime fecha;

	public Pedido(Long id, Cliente cliente, Comercial comercial, List<Vehiculo> vehiculos, LocalDateTime fecha,
			Estado estado, Factura factura) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.comercial = comercial;
		this.vehiculos = vehiculos;
		this.fecha = fecha;
		this.estado = estado;
		this.factura = factura;
	}

	public Pedido(Long id) {
		super();
		this.id = id;
	}
	
	public Pedido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Comercial getComercial() {
		return comercial;
	}

	public void setComercial(Comercial comercial) {
		this.comercial = comercial;
	}

	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}

	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", cliente=" + cliente + ", comercial=" + comercial + ", vehiculos=" + vehiculos
				+ ", fecha=" + fecha + ", estado=" + estado + ", factura=" + factura + "]";
	}

}
