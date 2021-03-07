package com.project.security.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.model.entity.LibraryUser;
import com.project.model.repository.LibraryUserRepository;
import com.project.security.model.SecurityUser;
import com.project.security.model.dto.SecurityUserDTO;
import com.project.security.model.dto.SecurityUserDTOConverter;
import com.project.security.model.repository.SecurityUserRepository;

@Service("userService")
public class SecurityUserService implements UserDetailsService {
	
	@Autowired
	private SecurityUserRepository segurityuserrepository;
	
	@Autowired
	private LibraryUserRepository libraryUserRepository;
	
	@Autowired
	private SecurityUserDTOConverter converter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return segurityuserrepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
	}
	
	public UserDetails loadUserById(Integer idUser) throws AuthenticationException {
		return segurityuserrepository.findById(idUser)
				.orElseThrow(()-> new AuthenticationException("Id/username not found"));
	}	
	
	public SecurityUserDTO createNewUser(SecurityUserDTO userDTO) {
		SecurityUser segurityUser = segurityuserrepository.save(converter.fromSegurityUserDTOToSegurityUser(userDTO));
		
		LibraryUser libraryUser = libraryUserRepository.save(segurityUser.getLibraryUser());
		
		segurityUser.setLibraryUser(libraryUser);
		
		
		
		return converter.fromSegurityUserToSegurityUserDTO(segurityuserrepository.save(segurityUser)); 
	}
}
