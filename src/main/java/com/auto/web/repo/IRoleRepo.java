package com.auto.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auto.web.models.Role;

public interface IRoleRepo extends JpaRepository<Role, Long> {

}
