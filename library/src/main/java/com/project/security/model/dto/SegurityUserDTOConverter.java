package com.project.security.model.dto;

import org.springframework.stereotype.Component;

import com.project.security.model.SegurityUser;

@Component
public class SegurityUserDTOConverter {
	public SegurityUser fromSegurityUserDTOToSegurityUser(SegurityUserDTO dto) {
		SegurityUser user  = new SegurityUser(dto.getUsername(), dto.getPassword(), dto.getName(), dto.getSurname(), dto.getDni(), dto.getBirthdate(), dto.getNumber());
		
		return user;
	}
	
	public SegurityUserDTO fromSegurityUserToSegurityUserDTO(SegurityUser user) {
		SegurityUserDTO dto = new SegurityUserDTO();
		
		dto.setUsername(user.getUsername());
		dto.setPassword(user.getPassword());
		dto.setRoles(user.getRoles());
		
		return dto;
	}
	
}
