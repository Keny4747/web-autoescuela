package com.auto.web.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import org.springframework.validation.Validator;

import com.auto.web.models.Auto;

@Component
public class AutoValidador implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Auto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Auto auto = (Auto)target;
		
		if(!auto.getPlaca().matches("([A-Z]{1}[\\d][\\w]{1}[-][\\d]{3})")) {
			errors.rejectValue("placa","Pattern.auto.placa");
		}
	}

}
