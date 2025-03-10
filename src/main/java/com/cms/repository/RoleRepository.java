package com.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
}
