package com.project.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.security.model.dto.SecurityUserDTO;
import com.project.security.service.SecurityUserService;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class SecurityUserController {
	
	@Autowired
	private SecurityUserService userService;
	
	
	@PostMapping("/sign-in")
	public ResponseEntity<SecurityUserDTO> signin(@RequestBody SecurityUserDTO userDTO) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(userDTO));
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/log-in")
	public ResponseEntity<SecurityUserDTO> login(@RequestBody SecurityUserDTO userDTO){
		
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}
}
