package com.auto.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

	@GetMapping({"/","","/index"})
	public String principal(){
		return "index";
	}
}
