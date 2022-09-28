package com.auto.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auto.web.models.Plan;
@Repository
public interface IPlanRepo extends JpaRepository<Plan, Integer>{

}
