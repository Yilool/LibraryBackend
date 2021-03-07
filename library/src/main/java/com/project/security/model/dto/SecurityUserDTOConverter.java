package com.project.security.model.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.model.repository.LibraryUserRepository;
import com.project.security.model.SecurityUser;

@Component
public class SecurityUserDTOConverter {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public SecurityUser fromSegurityUserDTOToSegurityUser(SecurityUserDTO dto) {
		SecurityUser user = new SecurityUser(dto.getUsername(), passwordEncoder.encode(dto.getPassword()),
				dto.getName(), dto.getSurname(), dto.getDni(), dto.getBirthdate(), dto.getNumber());
		
		return user;
	}

	public SecurityUserDTO fromSegurityUserToSegurityUserDTO(SecurityUser user) {
		SecurityUserDTO dto = new SecurityUserDTO();

		dto.setUsername(user.getUsername());
		dto.setRoles(user.getRoles());

		return dto;
	}

}
