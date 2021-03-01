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

import com.project.model.entity.Book;
import com.project.service.BookService;

@CrossOrigin
@RestController
@RequestMapping(path = "/book")
public class BookController {
	@Autowired
	private BookService bookService;

	@GetMapping
	public ResponseEntity<?> getBooks() {
		ResponseEntity<?> response;

		try {
			List<Book> results = bookService.getAllBook();
			response = ResponseEntity.ok(results);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@PostMapping
	public ResponseEntity<?> addBook(@RequestBody Book book) {
		ResponseEntity<?> response;

		try {
			Book book2 = bookService.addBook(book);
			response = ResponseEntity.ok(book2);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> getBookByTitle(@PathVariable Integer id) {
		ResponseEntity<?> response;

		try {
			Book book = bookService.getBookById(id);
			response = ResponseEntity.ok(book);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Integer id, @RequestBody Book book) {
		ResponseEntity<?> response;

		try {
			Book book2 = bookService.updateBook(id, book);
			response = ResponseEntity.ok(book2);
		} catch (Exception e) { 
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable Integer id) {
		ResponseEntity<?> response;

		try {
			Book book = bookService.deleteBook(id);
			response = ResponseEntity.ok(book);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}
}