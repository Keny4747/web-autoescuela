package com.auto.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.auto.web.models.Plan;

public interface IPlanService extends ICRUD<Plan, Integer>{
	Page<Plan> findAllPage(Pageable pageable);
}
