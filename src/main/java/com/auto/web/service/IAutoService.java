package com.auto.web.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.auto.web.models.Auto;

public interface IAutoService extends ICRUD<Auto, Integer>{
	Page<Auto> findAllPage(Pageable pageable);
}
