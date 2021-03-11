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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.model.dto.BookDTO;
import com.project.service.BookService;

@CrossOrigin
@RestController
@RequestMapping(path = "/book")
public class BookController { 
	@Autowired
	private BookService bookService;

	private Logger log;
	
	@GetMapping
	public ResponseEntity<?> getBooks() {
		ResponseEntity<?> response;

		try {
			List<BookDTO> results = bookService.getAllBook();
			response = ResponseEntity.ok(results);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@PostMapping
	public ResponseEntity<?> addBook(@RequestBody BookDTO book) {
		ResponseEntity<?> response;

		try {
			BookDTO book2 = bookService.addBook(book);
			response = ResponseEntity.ok(book2);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getBookByTitle(@PathVariable Integer id) {
		ResponseEntity<?> response;

		try {
			BookDTO book = bookService.getBookById(id);
			response = ResponseEntity.ok(book);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody BookDTO book) {
		ResponseEntity<?> response;

		try {
			BookDTO book2 = bookService.updateBook(id, book);
			response = ResponseEntity.ok(book2);
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
			BookDTO book = bookService.deleteBook(id);
			response = ResponseEntity.ok(book);
		} catch (Exception e) {
			log.error(e.getMessage());
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}
}
