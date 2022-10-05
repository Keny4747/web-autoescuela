package com.auto.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.auto.web.models.Plan;
import com.auto.web.repo.IPlanRepo;
import com.auto.web.service.IPlanService;

@Service
public class PlanServiceImpl implements IPlanService{
	
	@Autowired
	IPlanRepo planRepo;
	
	@Override
	public void create(Plan plan) {
		planRepo.save(plan);
	}

	@Override
	public List<Plan> findAll() {
		
		return planRepo.findAll();
	}

	@Override
	public Plan findOne(Integer id) {
		return planRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		planRepo.deleteById(id);
	}

	@Override
	public Page<Plan> findAllPage(Pageable pageable) {
		return planRepo.findAll(pageable);
	}

}
