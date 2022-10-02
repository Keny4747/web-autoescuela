package com.auto.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.auto.web.models.Instructor;
import com.auto.web.service.IInstructorService;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	

	@Autowired
	IInstructorService instructorService;
	
	@GetMapping("/form")
	public String form(Instructor instructor, Model model) {
		model.addAttribute("auto", instructor);
		model.addAttribute("titulo", "Registro de Instructor");

		return"instructor/form";
	}
	
	@PostMapping("/form")
	public String crear(Instructor instructor,Model model) {
		instructorService.create(instructor);
		return "redirect:/instructor/listar";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de Instructores");
		model.addAttribute("lista", instructorService.findAll());
		return "instructor/listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model) {

		Instructor instructor = null;

		if (id > 0) {
			instructor = instructorService.findOne(id);
			
		} else {
			
			return "redirect:/instructor/listar";
		}
		model.addAttribute("instructor", instructor);
		model.addAttribute("titulo", "Editar Instructor");
		
		return "instructor/form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id) {

		if (id > 0) {
			
			instructorService.delete(id);
			
		}
		return "redirect:/instructor/listar";
	}
	
}
