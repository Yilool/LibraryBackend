package com.project.security.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.security.model.SecurityUser;


public interface SecurityUserRepository extends JpaRepository<SecurityUser, Integer>{

	public Optional<SecurityUser> findByUsername(String username);
	
	public SecurityUser findSegurityUserByUsername(String username);
}
