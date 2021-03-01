package com.project.security.controller;

import static com.project.security.common.SecurityConstants.HEADER_STRING;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.security.model.SegurityUser;
import com.project.security.model.dto.SegurityUserDTO;
import com.project.security.service.UserService;

@CrossOrigin(exposedHeaders = HEADER_STRING)
@RestController
@RequestMapping("/user")
public class UserController {

	private SegurityUserDTO user;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/sign-in")
	public ResponseEntity<SegurityUserDTO> signin(@RequestBody SegurityUserDTO userDTO) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(userService.createNewUser(userDTO));
		}catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/log-in")
	public ResponseEntity<SegurityUserDTO> login(@RequestBody SegurityUserDTO userDTO){
		this.user = userService.getUser(userDTO);
		
		return ResponseEntity.status(HttpStatus.OK).body(userDTO);
	}
	
	@GetMapping("/loged")
	public ResponseEntity<SegurityUserDTO> getUserAutenticado() throws Exception {
		if (this.user == null) {
			throw new Exception("No user are loged");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(this.user);
	}
}
