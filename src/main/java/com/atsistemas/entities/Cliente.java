package com.atsistemas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@NotNull
	private String name;
	
	private String telefono;
	
	private String correo;
	
	@ManyToOne
	@JoinColumn(name="comercial_id")
	//Ignore certain entity fields which in the end bend over the infinite recursion
	@JsonIgnoreProperties("clientes")
	private Comercial comercial;
	
	@OneToMany(mappedBy="cliente")
	@JsonIgnoreProperties("cliente")
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente(Long id, String name, String telefono, String correo, Comercial comercial,List<Pedido> pedidos) {
		super();
		this.id = id;
		this.name = name;
		this.telefono = telefono;
		this.correo = correo;
		this.comercial = comercial;
		this.pedidos = pedidos;
	}
	
	public Cliente(Long id) {
		super();
		this.id = id;
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Comercial getComercial() {
		return comercial;
	}

	public void setComercial(Comercial comercial) {
		this.comercial = comercial;
	}
	
}
