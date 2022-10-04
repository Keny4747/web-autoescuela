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
	public String create(@Valid Plan plan,BindingResult result ,Model model, RedirectAttributes flash) {
		

		if (result.hasErrors()) {

			model.addAttribute("plan", plan);
			model.addAttribute("titulo", "Registro de Plan");
			return "plan/form";
		}
		String mensajeFlash = (plan.getId()!=null)?"Plan de estudios editado con éxito!":"Plan de estudios agreado con éxito!";
		
		
		planService.create(plan);
		flash.addFlashAttribute("success",mensajeFlash);
		return "redirect:/plan/listar";
	}

	@GetMapping("/listar")
	public String form(Model model) {

		model.addAttribute("lista", planService.findAll());
		model.addAttribute("titulo", "Lista de plan de estudios");
		return "plan/listar";
	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Plan plan = null;

		if (id > 0) {
			plan = planService.findOne(id);

		} else {
			flash.addFlashAttribute("error","El ID del plan de estudios no puede ser cero!");

			return "redirect:/plan/listar";
		}
		model.addAttribute("plan", plan);
		model.addAttribute("titulo", "Editar plan de estudio");

		return "plan/form";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id, RedirectAttributes flash) {

		if (id > 0) {

			planService.delete(id);
			flash.addFlashAttribute("success","Plan de estudios eliminado con éxito!");

		}
		return "redirect:/plan/listar";
	}
}
