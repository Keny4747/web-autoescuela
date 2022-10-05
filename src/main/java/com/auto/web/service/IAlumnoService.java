package com.auto.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.auto.web.models.Alumno;

public interface IAlumnoService extends ICRUD<Alumno, Integer>{
	
	Page<Alumno> findAllPage(Pageable pageable);
}
