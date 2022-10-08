package com.auto.web.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.auto.web.models.Alumno;
import com.auto.web.models.Clase;
import com.auto.web.models.Plan;
import com.auto.web.pagination.PageRender;
import com.auto.web.service.IAlumnoService;
import com.auto.web.service.IClaseService;
import com.auto.web.service.IPlanService;
import com.auto.web.view.pdf.AlumnoPdfView;
import com.lowagie.text.DocumentException;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {
	
	@Autowired
	IAlumnoService alumnoServicio;
	
	@Autowired
	IPlanService planService;
	
	@Autowired
	IClaseService claseService;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	

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
	public String crear(@Valid Alumno alumno,BindingResult result ,Model model, RedirectAttributes flash) {
		
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
		
		String mensajeFlash = (alumno.getId()!=null)?"Alumno editado con éxito!":"Alumno agreado con éxito!";
		
		alumnoServicio.create(alumno);
		flash.addFlashAttribute("success",mensajeFlash);
		return "redirect:/alumno/listar";
	}
	

	
	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Integer id, Model model, RedirectAttributes flash) {

		Alumno alumno = null;

		if (id > 0) {
			alumno = alumnoServicio.findOne(id);
			
			if(alumno == null) {
				flash.addFlashAttribute("error","El ID del alumno no existe en la base de datos...");
			}
			
		} else {
			flash.addFlashAttribute("error","El ID del alumno no puede ser cero!");
			return "redirect:/alumno/listar";
		}
		
		
		model.addAttribute("alumno", alumno);
		model.addAttribute("titulo", "Editar alumno");
		model.addAttribute("listaPlan", planService.findAll());
		return "alumno/form";
	}
	
	
	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Integer id, RedirectAttributes flash) {

		if (id > 0) {
			
			alumnoServicio.delete(id);
			flash.addFlashAttribute("success","Alumno eliminado con éxito!");
			
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
	
	
	@GetMapping("/listar")
	public String listar(@RequestParam(name = "page", defaultValue = "0")int page, Model model, Authentication authentication) {
		
		if(authentication!=null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}
		
		Pageable pageRequest = PageRequest.of(page, 8);
		Page<Alumno> listaAlumnos = alumnoServicio.findAllPage(pageRequest);
		PageRender<Alumno> pageRender = new PageRender<>("/alumno/listar", listaAlumnos); 
		
		model.addAttribute("titulo", "Lista de alumnos");
		model.addAttribute("listaAlumnos", listaAlumnos);
		model.addAttribute("page", pageRender);
		
	
		return "alumno/listar";
	}
	
	
	@GetMapping("/exportarPDF")
	public void exportarListadoDeAlumnos(HttpServletResponse response) throws DocumentException, IOException{
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());
		
		String cabecera = "Content-Disposition";
		String valor = "attachment; filename-Alumnos_" + fechaActual + ".pdf";
		
		response.setHeader(cabecera, valor);
		
		List<Alumno> alumnos = alumnoServicio.findAll();
		
		AlumnoPdfView exporter = new AlumnoPdfView(alumnos);
		exporter.exportar(response);
	} 
	/*
	@GetMapping("/listar")
	public String listar(Model model) {
		List<Alumno> alumnos = alumnoServicio.findAll();
		model.addAttribute("titulo", "Lista de alumnos");
		model.addAttribute("listaAlumnos", alumnos);
		return "alumno/listar";
	}
	*/
}
