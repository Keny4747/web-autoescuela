package com.auto.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auto.web.models.Instructor;
@Repository
public interface IInstructorRepo extends JpaRepository<Instructor, Integer>{

}
