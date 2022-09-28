package com.auto.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.auto.web.models.Plan;
import com.auto.web.service.IPlanService;

@Controller
public class PlanController {
	
	@Autowired
	IPlanService planService;
	
	@GetMapping("/formPlan")
	public String form(Plan plan, Model model) {
		
		model.addAttribute("titulo", "Registro de Plan de estudios");
		model.addAttribute("plan", plan);
		return "plan/form";
	}
	
	@PostMapping("/formPlan")
	public String create(Plan plan,Model model) {
		planService.create(plan);
	
		return "redirect:/listarPlan";
	}
	
	@GetMapping("/listarPlan")
	public String form(Model model) {
		
		model.addAttribute("lista", planService.findAll());
		model.addAttribute("titulo","Lista de plan de estudios");
		return "plan/listar";
	}
}
