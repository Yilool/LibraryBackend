package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@GetMapping
	public ResponseEntity<?> getAllBorrows(@PathVariable String username) {
		ResponseEntity<?> response;

		try {
			List<BorrowDTO> borrowDTO = borrowService.getUserBorrowsByUsername(username);
			response = ResponseEntity.ok(borrowDTO);
		} catch (Exception e) {
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
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@PutMapping
	public ResponseEntity<?> extendBorrow(@RequestBody BorrowDTO borrowDTO) {
		ResponseEntity<?> response;

		try {
			BorrowDTO borrowDTO2 = borrowService.extendDeliveryDate(borrowDTO);
			response = ResponseEntity.ok(borrowDTO2);
		} catch (Exception e) { 
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@DeleteMapping
	public ResponseEntity<?> deleteBook(@RequestBody BorrowDTO borrowDTO) {
		ResponseEntity<?> response;

		try {
			BorrowDTO borrowDTO2 = borrowService.deleteBorrow(borrowDTO);
			response = ResponseEntity.ok(borrowDTO2);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}
}
