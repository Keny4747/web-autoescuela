package com.auto.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.auto.web.models.Instructor;

public interface IInstructorService extends ICRUD<Instructor, Integer>{
	Page<Instructor> findAllPage(Pageable pageable);
}
