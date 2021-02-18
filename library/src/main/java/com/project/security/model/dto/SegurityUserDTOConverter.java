package com.project.security.model.dto;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.project.security.model.SegurityUser;
import com.project.security.model.SegurityUserRole;

@Component
public class SegurityUserDTOConverter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public SegurityUser fromSegurityUserDTOToSegurityUser(SegurityUserDTO dto) {
		SegurityUser user  = new SegurityUser();
		
		user.setUsername(dto.getUsername());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setRoles(Set.of(SegurityUserRole.USER));
		user.setCreateTime(LocalDateTime.now());
		user.setUpdateTime(LocalDateTime.now());
		user.setLastPasswordChange(LocalDateTime.now());
		user.setLocked(false);
		user.setEnabled(true);
		user.setAuthenticationAttempts(3);
		user.setPasswordPolicyExpDate(LocalDateTime.now().plusDays(365));
		
		return user;
		
	}
	
	public SegurityUserDTO fromSegurityUserToSegurityUserDTO(SegurityUser user) {
		SegurityUserDTO dto = new SegurityUserDTO();
		
		dto.setUsername(user.getUsername());
		dto.setRoles(user.getRoles());
		
		return dto;
	}
	
}
