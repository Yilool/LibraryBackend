package com.project.security.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.security.model.SegurityUser;


public interface SegurityUserRepository extends JpaRepository<SegurityUser, Integer>{

	public Optional<SegurityUser> findByUsername(String username);
}
