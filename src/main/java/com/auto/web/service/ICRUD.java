package com.auto.web.service;

import java.util.List;

public interface ICRUD <T,ID> {

	void create(T t);
	
	List<T> findAll();
	
	T findOne(ID id);
	
	void delete(ID id);
}
