package com.auto.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.auto.web.auth.handler.LoginSucceesHandler;


@SuppressWarnings("deprecation")
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	 private LoginSucceesHandler succeesHandler;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
		
		PasswordEncoder encoder = passwordEncoder();
		/*Codificacion de password por cada usuario*/
		UserBuilder users = User.builder().passwordEncoder(password -> {
			return encoder.encode(password);
		});
		
		//las contraseñas se envian al UserBuilder
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN","USER"))//el user admin va a tener 2 roles admin y user
		.withUser(users.username("keny").password("12345").roles("USER"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**","/js/**","/images/**","/**/ver/**").permitAll()
		.antMatchers("/").hasAnyRole("USER")
		.antMatchers("/alumno/ver/**").hasAnyRole("ADMIN")	
		.antMatchers("/**/form/**").hasAnyRole("ADMIN")
		.antMatchers("/**/eliminar/**").hasAnyRole("ADMIN")
		.antMatchers("/**/editar/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()	
			.formLogin()
				.successHandler(succeesHandler)
				.loginPage("/login")		
			.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
	}
	
	
}
