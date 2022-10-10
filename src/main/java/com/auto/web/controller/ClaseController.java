package com.auto.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.auto.web.models.Alumno;
import com.auto.web.models.Clase;
import com.auto.web.service.IAlumnoService;
import com.auto.web.service.IAutoService;
import com.auto.web.service.IClaseService;
import com.auto.web.service.IInstructorService;

@Controller
@RequestMapping("/clase")
public class ClaseController {

	@Autowired
	private IClaseService claseService;
	
	@Autowired
	private IAlumnoService alumnoService;
	
	@Autowired
	private IAutoService autoService;
	
	@Autowired
	private IInstructorService instructorService;
	
	
	@GetMapping("/form/{id}")
	public String crear(@PathVariable(value = "id") Integer id, Model model) {
		Alumno alumno;
		Clase clase = new Clase();
		alumno = alumnoService.findOne(id);
		clase.setAlumno(alumno);
	
		
		model.addAttribute("clase", clase);
		model.addAttribute("alumno", alumno);
		model.addAttribute("instructores", instructorService.findAll());
		model.addAttribute("autos", autoService.findAll());
		model.addAttribute("titulo", "Registro de clase del alumno: "+alumno.getNombre()+" "+alumno.getApellido());
		return "clase/form";
	}
	
	@PostMapping("/form")
	public String crear(Clase clase, Model model) {
		claseService.create(clase);	
		return "redirect:/clase/listar";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de clases");
		model.addAttribute("lista", claseService.findAll());
		return "clase/listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model) {

		Clase clase = null;

		if (id > 0) {
			clase = claseService.findOne(id);
			
		} else {
			
			return "redirect:/clase/listar";
		}
		
		model.addAttribute("clase", clase);
		model.addAttribute("titulo", "Editar Clase");
		
		return "clase/form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id) {

		if (id > 0) {
			
			claseService.delete(id);	
		}
		
		return "redirect:/clase/listar";
	}
}
