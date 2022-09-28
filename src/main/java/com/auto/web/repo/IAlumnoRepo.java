package com.auto.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.auto.web.models.Alumno;
@Repository
public interface IAlumnoRepo extends JpaRepository<Alumno, Integer>{

}
