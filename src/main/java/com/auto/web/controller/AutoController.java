package com.auto.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.auto.web.models.Auto;
import com.auto.web.service.IAutoService;
import com.auto.web.validation.AutoValidador;

@Controller
@RequestMapping("/auto")
public class AutoController {
	
	@Autowired
	IAutoService autoService;
	
	@Autowired
	private AutoValidador validador;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
	}
	
	@GetMapping("/form")
	public String form(Auto auto, Model model) {
		model.addAttribute("auto", auto);
		model.addAttribute("titulo", "Registro de Autos");

		return"auto/form";
	}
	
	@PostMapping("/form")
	public String crear(@Valid Auto auto,BindingResult result ,Model model, RedirectAttributes flash) {
		
		//validador.validate(auto, result);
		
		if(result.hasErrors()) {
			
			model.addAttribute("auto", auto);
			model.addAttribute("titulo", "Registro de Autos");
			return "auto/form";
		}
		
		String mensajeFlash = (auto.getId()!=null)?"Auto editado con éxito!":"Auto agreado con éxito!";
		
		autoService.create(auto);
		flash.addFlashAttribute("success",mensajeFlash);
		return "redirect:/auto/listar";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de Autos");
		model.addAttribute("lista", autoService.findAll());
		return "auto/listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Auto auto = null;

		if (id > 0) {
			auto = autoService.findOne(id);
			
		} else {
			flash.addFlashAttribute("error","El ID del auto no puede ser cero!");
			return "redirect:/auto/listar";
		}
		model.addAttribute("auto", auto);
		model.addAttribute("titulo", "Editar auto");
		return "auto/form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id, RedirectAttributes flash) {

		if (id > 0) {
			
			autoService.delete(id);
			flash.addFlashAttribute("success","Auto eliminado con éxito!");
		}
		return "redirect:/auto/listar";
	}
}
