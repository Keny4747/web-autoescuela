package com.auto.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.auto.web.repo.IUsuarioRepo;

@Service("jpaUserDetailService")
public class JpaUserDetailsService implements UserDetailsService {
	
	@Autowired
	IUsuarioRepo usuarioRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

}
