package com.auto.web.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "alumno")
public class Alumno implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer dni;
	private String nombre;
	private String apellido;
	
	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecNac;
	
	@Column(name = "fecha_matricula")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecMatricula;
	
	private String genero;
	@Column(nullable = false)
	private String telefono;
	
	@ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinTable(name = "plan_alumno", joinColumns = @JoinColumn(name = "alumno_id"),
	inverseJoinColumns = @JoinColumn(name="plan_id"))
	private Set<Plan> planes;
	
	
	@PostPersist
	public void prePersist() {
		fecMatricula=new Date();
	}
	
	//Getters and setters
	
	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFecNac() {
		return fecNac;
	}

	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}

	public Date getFecMatricula() {
		return fecMatricula;
	}

	public void setFecMatricula(Date fecMatricula) {
		this.fecMatricula = fecMatricula;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




	public Set<Plan> getPlanes() {
		return planes;
	}

	public void setPlanes(Set<Plan> planes) {
		this.planes = planes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	@Override
	public String toString() {
		return "Alumno [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fecNac="
				+ fecNac + ", fecMatricula=" + fecMatricula + ", genero=" + genero + ", telefono=" + telefono
				+ ", planes=" + planes + "]";
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
