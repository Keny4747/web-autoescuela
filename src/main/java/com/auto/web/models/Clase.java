package com.auto.web.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "clases_alumno")
public class Clase implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Alumno alumno;
	
	@Column(name = "fecha_clase")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fecClase;
	
	@OneToOne
	@JoinColumn(name = "auto_placa", referencedColumnName = "placa")
	private Auto auto;
	
	@OneToOne
	@JoinColumn(name = "instructor_id", referencedColumnName = "id")
	private Instructor instructor;
	
	
	@PrePersist
	public void prePersist() {
		fecClase=new Date();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Alumno getAlumno() {
		return alumno;
	}


	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	public Date getFecClase() {
		return fecClase;
	}


	public void setFecClase(Date fecClase) {
		this.fecClase = fecClase;
	}


	public Auto getAuto() {
		return auto;
	}


	public void setAuto(Auto auto) {
		this.auto = auto;
	}


	public Instructor getInstructor() {
		return instructor;
	}


	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
