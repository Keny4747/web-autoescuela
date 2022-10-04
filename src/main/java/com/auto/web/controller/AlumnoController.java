package com.auto.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.auto.web.models.Alumno;
import com.auto.web.models.Clase;
import com.auto.web.models.Plan;
import com.auto.web.service.IAlumnoService;
import com.auto.web.service.IClaseService;
import com.auto.web.service.IPlanService;




@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	@Autowired
	IAlumnoService alumnoServicio;
	
	@Autowired
	IPlanService planService;
	
	@Autowired
	IClaseService claseService;
	
	@GetMapping("/form")
	public String form(Model model) {
		Alumno alumno = new Alumno();
		
		List<Plan> listaPlan = planService.findAll();
		model.addAttribute("alumno", alumno);		
		model.addAttribute("listaPlan", listaPlan);
		model.addAttribute("titulo", "Registro de Alumnos");
		return "alumno/form";
	}
	 
	@PostMapping("/form")
	public String crear(@Valid Alumno alumno,BindingResult result ,Model model) {
		
		if(result.hasErrors()) {
			Map<String, String> errores = new HashMap<>();
			result.getFieldErrors().forEach(err ->{
				errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});
			model.addAttribute("error", errores);
			model.addAttribute("listaPlan", planService.findAll());
			model.addAttribute("titulo", "Registro de Alumnos");
			return "alumno/form";
		}
		
		alumnoServicio.create(alumno);
		return "redirect:/alumno/listar";
	}
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Alumno> alumnos = alumnoServicio.findAll();
		model.addAttribute("titulo", "Lista de alumnos");
		model.addAttribute("listaAlumnos", alumnos);
		return "alumno/listar";
	}
	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model) {

		Alumno alumno = null;

		if (id > 0) {
			alumno = alumnoServicio.findOne(id);
			
		} else {
			
			return "redirect:/alumno/listar";
		}
		model.addAttribute("alumno", alumno);
		model.addAttribute("titulo", "Editar alumno");
		model.addAttribute("listaPlan", planService.findAll());
		return "alumno/form";
	}
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id) {

		if (id > 0) {
			
			alumnoServicio.delete(id);
			
		}
		return "redirect:/alumno/listar";
	}
	
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Integer id, Model model) {

		Alumno alumno = alumnoServicio.findOne(id);
		
		List<Clase> listaClases = claseService.mostrarClases(id);
		if (alumno == null) {
			
			return "redirect:/alumno/listar";
		}

		model.addAttribute("alumno", alumno);
		model.addAttribute("titulo", "Detalle alumno: " + alumno.getNombre()+" "+alumno.getApellido());
		model.addAttribute("listaClases", listaClases);
		return "alumno/ver";
	}
}
