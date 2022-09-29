package com.auto.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
