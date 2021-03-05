package com.project.security.model.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.model.repository.LibraryUserRepository;
import com.project.security.model.SegurityUser;

@Component
public class SegurityUserDTOConverter {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private LibraryUserRepository libraryUserRepository;

	public SegurityUser fromSegurityUserDTOToSegurityUser(SegurityUserDTO dto) {
		SegurityUser user = new SegurityUser(dto.getUsername(), passwordEncoder.encode(dto.getPassword()),
				dto.getName(), dto.getSurname(), dto.getDni(), dto.getBirthdate(), dto.getNumber());
		
		libraryUserRepository.save(user.getLibraryUser());
		
		return user;
	}

	public SegurityUserDTO fromSegurityUserToSegurityUserDTO(SegurityUser user) {
		SegurityUserDTO dto = new SegurityUserDTO();

		dto.setUsername(user.getUsername());
		dto.setRoles(user.getRoles());

		return dto;
	}

}
