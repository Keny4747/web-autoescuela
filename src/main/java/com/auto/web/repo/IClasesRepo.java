package com.auto.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auto.web.models.Clase;

@Repository
public interface IClasesRepo extends JpaRepository<Clase, Integer>{

}
