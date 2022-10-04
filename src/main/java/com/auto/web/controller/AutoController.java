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
	public String crear(@Valid Auto auto,BindingResult result ,Model model) {
		
		//validador.validate(auto, result);
		
		if(result.hasErrors()) {
			
			model.addAttribute("auto", auto);
			model.addAttribute("titulo", "Registro de Autos");
			return "auto/form";
		}
		
		
		autoService.create(auto);
		return "redirect:/auto/listar";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de Autos");
		model.addAttribute("lista", autoService.findAll());
		return "auto/listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model) {

		Auto auto = null;

		if (id > 0) {
			auto = autoService.findOne(id);
			
		} else {
			
			return "redirect:/auto/listar";
		}
		model.addAttribute("auto", auto);
		model.addAttribute("titulo", "Editar auto");
		return "auto/form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id) {

		if (id > 0) {
			
			autoService.delete(id);
			
		}
		return "redirect:/auto/listar";
	}
}
