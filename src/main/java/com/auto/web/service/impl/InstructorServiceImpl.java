package com.auto.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.auto.web.models.Instructor;
import com.auto.web.repo.IInstructorRepo;
import com.auto.web.service.IInstructorService;

@Service
public class InstructorServiceImpl implements IInstructorService {

	@Autowired
	IInstructorRepo instructorRepo;
	
	@Override
	public void create(Instructor instructor) {
		instructorRepo.save(instructor);
	}

	@Override
	public List<Instructor> findAll() {
		return instructorRepo.findAll();
	}

	@Override
	public Instructor findOne(Integer id) {
		
		return instructorRepo.findById(id).orElse(null);
	}

	@Override
	public void delete(Integer id) {
		 instructorRepo.deleteById(id);
	}

	@Override
	public Page<Instructor> findAllPage(Pageable pageable) {
		
		return instructorRepo.findAll(pageable);
	}

}
