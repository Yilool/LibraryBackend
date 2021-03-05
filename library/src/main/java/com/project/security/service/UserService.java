package com.project.security.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.security.model.dto.SegurityUserDTO;
import com.project.security.model.dto.SegurityUserDTOConverter;
import com.project.security.model.repository.SegurityUserRepository;

@Service("userService")
public class UserService implements UserDetailsService {
	
	@Autowired
	private SegurityUserRepository segurityuserrepository;
	
	@Autowired
	private SegurityUserDTOConverter converter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return segurityuserrepository.findByUsername(username)
				.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
	}
	
	public UserDetails loadUserById(Integer idUser) throws AuthenticationException {
		return segurityuserrepository.findById(idUser)
				.orElseThrow(()-> new AuthenticationException("Id/username not found"));
	}	
	
	public SegurityUserDTO createNewUser(SegurityUserDTO userDTO) {
		return converter.fromSegurityUserToSegurityUserDTO(segurityuserrepository.save(converter.fromSegurityUserDTOToSegurityUser(userDTO))); 
	}
}
