package com.atsistemas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class Vehiculo implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String descripcion;
	
	private String modelo;
	
	private String color;
	
	private String motor;
	
	@NotNull
	private double precio;
	
	//@ManyToOne
	//@JoinColumn(name="pedido_id")
	@ManyToMany(mappedBy="vehiculos")
	@JsonIgnoreProperties("vehiculos")
	private List<Pedido> pedidos = new ArrayList<>();


	public Vehiculo(Long id, String descripcion, String modelo, String color, String motor, double precio,
			List<Pedido> pedidos) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.modelo = modelo;
		this.color = color;
		this.motor = motor;
		this.precio = precio;
		this.pedidos = pedidos;
	}
	
	public Vehiculo(Long id) {
		super();
		this.id = id;
	}

	public Vehiculo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", descripcion=" + descripcion + ", modelo=" + modelo + ", color=" + color
				+ ", motor=" + motor + ", precio=" + precio + ", pedidos=" + pedidos + "]";
	}
	
}
