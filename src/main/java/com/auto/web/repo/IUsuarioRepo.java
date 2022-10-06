package com.auto.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auto.web.models.Usuario;

public interface IUsuarioRepo extends JpaRepository<Usuario, Long>{

}
