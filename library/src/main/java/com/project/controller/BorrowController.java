package com.project.controller;

import java.util.List;

import org.apache.logging.log4j.core.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.dto.BorrowDTO;
import com.project.service.BorrowService;

@CrossOrigin
@RestController
@RequestMapping(path = "/borrow")
public class BorrowController {
	@Autowired
	private BorrowService borrowService;
	
	private Logger log;

	@GetMapping(path = "/user/{username}")
	public ResponseEntity<?> getAllBorrows(@PathVariable String username) {
		ResponseEntity<?> response;

		try {
			List<BorrowDTO> borrowDTO = borrowService.getUserBorrowsByUsername(username);
			response = ResponseEntity.ok(borrowDTO);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}
	
	@PostMapping
	public ResponseEntity<?> newBorrow(@RequestBody BorrowDTO borrowDTO) {
		ResponseEntity<?> response;

		try {
			BorrowDTO borrowDTO2 = borrowService.newBorrow(borrowDTO);
			response = ResponseEntity.ok(borrowDTO2);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@GetMapping(path = "/borrow/{id}")
	public ResponseEntity<?> extendBorrow(@PathVariable Integer id) {
		ResponseEntity<?> response;

		try {
			BorrowDTO borrowDTO2 = borrowService.extendDeliveryDate(id);
			response = ResponseEntity.ok(borrowDTO2);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
		ResponseEntity<?> response;

		try {
			BorrowDTO borrowDTO2 = borrowService.deleteBorrow(id);
			response = ResponseEntity.ok(borrowDTO2);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}
}
