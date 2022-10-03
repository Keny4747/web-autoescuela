package com.auto.web.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auto.web.models.Clase;

@Repository
public interface IClasesRepo extends JpaRepository<Clase, Integer> {

	@Query(value = "SELECT * FROM clases_alumno c JOIN alumno a ON c.alumno_id = a.id WHERE a.id=?1", nativeQuery = true)
	public List<Clase> mostrarClases(Integer id);
}
