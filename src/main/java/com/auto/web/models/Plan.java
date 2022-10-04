package com.auto.web.models;

import java.io.Serializable;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "plan_matricula")
public class Plan implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty
	private String descripcion;
	
	@NotNull
	private Integer cantHoras;
	
	@NotNull
	private double precio;
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer codigo) {
		this.id = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCantHoras() {
		return cantHoras;
	}
	public void setCantHoras(Integer cantHoras) {
		this.cantHoras = cantHoras;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	

	
	@Override
	public String toString() {
		return descripcion;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
