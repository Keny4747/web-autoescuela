package com.auto.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auto.web.models.Alumno;
import com.auto.web.repo.IAlumnoRepo;
import com.auto.web.service.IAlumnoService;

@Service
public class AlumnoServiceImpl implements IAlumnoService {
	
	@Autowired
	IAlumnoRepo alumnoRepo;

	@Override
	public void create(Alumno alumno) {
		alumnoRepo.save(alumno);	
	}

	@Override
	public List<Alumno> findAll() {	
		return alumnoRepo.findAll();
	}

	@Override
	public Alumno findOne(Integer dni) {	
		return alumnoRepo.findById(dni).orElse(null);
	}

	@Override
	public void delete(Integer dni) {
		alumnoRepo.deleteById(dni);
	}
	
	
	
	
}
