package com.auto.web.service;

import java.util.List;

import com.auto.web.models.Clase;

public interface IClaseService extends ICRUD<Clase, Integer>{
	
	public List<Clase> mostrarClases(Integer id);
}
