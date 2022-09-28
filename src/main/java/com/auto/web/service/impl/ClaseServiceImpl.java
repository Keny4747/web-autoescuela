package com.auto.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auto.web.models.Clase;
import com.auto.web.repo.IClasesRepo;
import com.auto.web.service.IClaseService;

@Service
public class ClaseServiceImpl implements IClaseService{
	@Autowired
	IClasesRepo claseRepo;
	
	@Override
	public void create(Clase clase) {
		
		claseRepo.save(clase);
	}

	@Override
	public List<Clase> findAll() {
		
		return claseRepo.findAll();
	}

	@Override
	public Clase findOne(Integer id) {
	
		return claseRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		claseRepo.deleteById(id);
		
	}

}
