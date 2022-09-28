package com.auto.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auto.web.models.Auto;
import com.auto.web.repo.IAutoRepo;
import com.auto.web.service.IAutoService;

@Service
public class AutoServiceImpl implements IAutoService {
	@Autowired
	IAutoRepo autoRepo;

	@Override
	public void create(Auto auto) {
		autoRepo.save(auto);	
	}

	@Override
	public List<Auto> findAll() {
		
		return autoRepo.findAll();
	}

	@Override
	public Auto findOne(Integer id) {	
		return autoRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		autoRepo.deleteById(id);
		
	}
	
	
}
