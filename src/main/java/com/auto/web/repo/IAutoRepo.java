package com.auto.web.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auto.web.models.Auto;
@Repository
public interface IAutoRepo extends JpaRepository<Auto, Integer> {

}
