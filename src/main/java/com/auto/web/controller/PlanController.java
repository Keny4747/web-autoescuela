package com.auto.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.auto.web.models.Plan;
import com.auto.web.service.IPlanService;

@Controller
@RequestMapping("/plan")
public class PlanController {
	
	@Autowired
	IPlanService planService;
	
	@GetMapping("/form")
	public String form(Plan plan, Model model) {
		
		model.addAttribute("titulo", "Registro de Plan de estudios");
		model.addAttribute("plan", plan);
		return "plan/form";
	}
	
	@PostMapping("/form")
	public String create(Plan plan,Model model) {
		planService.create(plan);
	
		return "redirect:/plan/listar";
	}
	
	@GetMapping("/listar")
	public String form(Model model) {
		
		model.addAttribute("lista", planService.findAll());
		model.addAttribute("titulo","Lista de plan de estudios");
		return "plan/listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model) {

		Plan plan = null;

		if (id > 0) {
			plan = planService.findOne(id);

		} else {

			return "redirect:/plan/listar";
		}
		model.addAttribute("plan", plan);
		model.addAttribute("titulo", "Editar plan de estudio");

		return "plan/form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id) {

		if (id > 0) {

			planService.delete(id);

		}
		return "redirect:/plan/listar";
	}
}
