package com.auto.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.auto.web.models.Instructor;
import com.auto.web.service.IInstructorService;

@Controller
public class InstructorController {
	

	@Autowired
	IInstructorService instructorService;
	
	@GetMapping("/formInstructor")
	public String form(Instructor instructor, Model model) {
		model.addAttribute("auto", instructor);
		model.addAttribute("titulo", "Registro de Instructor");

		return"instructor/form";
	}
	
	@PostMapping("/formInstructor")
	public String crear(Instructor instructor,Model model) {
		instructorService.create(instructor);
		return "redirect:/listarInstructor";
	}
	
	@GetMapping("/listarInstructor")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de Instructores");
		model.addAttribute("lista", instructorService.findAll());
		return "instructor/listar";
	}
	
}
