package com.project.security.model.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.project.security.model.SegurityUserRole;

@JsonInclude(Include.NON_NULL)
public class SegurityUserDTO {
	
	private String username;
	private String password;
	private Set<SegurityUserRole> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<SegurityUserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SegurityUserRole> roles) {
		this.roles = roles;
	}

}
