package com.auto.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.auto.web.models.Alumno;
import com.auto.web.models.Plan;
import com.auto.web.service.IAlumnoService;
import com.auto.web.service.IPlanService;



@Controller
public class AlumnoController {
	
	@Autowired
	IAlumnoService alumnoServicio;
	
	@Autowired
	IPlanService planService;
	
	@GetMapping("/form")
	public String form(Model model) {
		Alumno alumno = new Alumno();
		
		List<Plan> listaPlan = planService.findAll();
		model.addAttribute("alumno", alumno);		
		model.addAttribute("listaPlan", listaPlan);
		model.addAttribute("titulo", "Registro de Alumnos");
		return "form";
	}
	 
	@PostMapping("/form")
	public String crear(Alumno alumno,Model model) {
		alumnoServicio.create(alumno);
		return "redirect:/listar";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Alumno> alumnos = alumnoServicio.findAll();
		model.addAttribute("titulo", "Lista de alumnos");
		model.addAttribute("listaAlumnos", alumnos);
		return "listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model) {

		Alumno alumno = null;

		if (id > 0) {
			alumno = alumnoServicio.findOne(id);
			
		} else {
			
			return "redirect:/listar";
		}
		model.addAttribute("alumno", alumno);
		model.addAttribute("titulo", "Editar alumno");
		model.addAttribute("listaPlan", planService.findAll());
		return "form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id) {

		if (id > 0) {
			
			alumnoServicio.delete(id);
			
		}
		return "redirect:/listar";
	}
}
