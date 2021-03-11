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

import com.project.model.dto.BookShelfDTO;
import com.project.service.BookShelfService;

@CrossOrigin
@RestController
@RequestMapping(path = "/bookshelf")
public class BookShelfController {
	@Autowired
	BookShelfService bookShelfService;
	
	private Logger log;

	@GetMapping
	private ResponseEntity<?> getAllBookShelves() {
		ResponseEntity<?> response;

		try {
			List<BookShelfDTO> result = bookShelfService.getAllBookShelves();
			response = ResponseEntity.ok(result);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@PostMapping
	public ResponseEntity<?> addBookShelf(@RequestBody BookShelfDTO bookShelf) {
		ResponseEntity<?> response;

		try {
			BookShelfDTO bookShelf2 = bookShelfService.addBookShelf(bookShelf);
			response = ResponseEntity.ok(bookShelf2);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
		ResponseEntity<?> response;

		try {
			BookShelfDTO bookShelf = bookShelfService.deleteBookShelf(id);
			response = ResponseEntity.ok(bookShelf);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

}
