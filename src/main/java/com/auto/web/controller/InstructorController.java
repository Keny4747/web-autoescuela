package com.auto.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.auto.web.models.Instructor;
import com.auto.web.service.IInstructorService;

@Controller
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	IInstructorService instructorService;
	

	

	@GetMapping("/form")
	public String form( Model model) {
		Instructor instructor = new Instructor();
		model.addAttribute("instructor", instructor);
		model.addAttribute("titulo", "Registro de Instructor");

		return "instructor/form";
	}

	@PostMapping("/form")
	public String crear(@Valid Instructor instructor, BindingResult result, Model model, RedirectAttributes flash) {
			
		if (result.hasErrors()) {
			
			model.addAttribute("instructor", instructor);
			model.addAttribute("titulo", "Registro de Instructor");
			return "instructor/form";
		}
		String mensajeFlash = (instructor.getId()!=null)?"Instructor editado con éxito!":"Instructor agreado con éxito!";
		
		
		instructorService.create(instructor);
		flash.addFlashAttribute("success",mensajeFlash);
		return "redirect:/instructor/listar";
	}

	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de Instructores");
		model.addAttribute("lista", instructorService.findAll());
		return "instructor/listar";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Instructor instructor = null;

		if (id > 0) {
			instructor = instructorService.findOne(id);

		} else {
			flash.addFlashAttribute("error","El ID del instructor no puede ser cero!");
			return "redirect:/instructor/listar";
		}
		model.addAttribute("instructor", instructor);
		model.addAttribute("titulo", "Editar Instructor");

		return "instructor/form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id, RedirectAttributes flash) {

		if (id > 0) {

			instructorService.delete(id);
			flash.addFlashAttribute("success","Instructor eliminado con éxito!");
		}
		return "redirect:/instructor/listar";
	}

}
