package com.project.controller;

import java.util.List;

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

import com.project.model.entity.BookShelf;
import com.project.service.BookShelfService;

@CrossOrigin
@RestController
@RequestMapping(path = "/bookshelf")
public class BookShelfController {
	@Autowired
	BookShelfService bookShelfService;

	@GetMapping
	private ResponseEntity<?> getAllBookShelves() {
		ResponseEntity<?> response;

		try {
			List<BookShelf> result = bookShelfService.getAllBookShelves();
			response = ResponseEntity.ok(result);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@PostMapping
	public ResponseEntity<?> addBookShelf(@RequestBody BookShelf bookShelf) {
		ResponseEntity<?> response;

		try {
			BookShelf bookShelf2 = bookShelfService.addBookShelf(bookShelf);
			response = ResponseEntity.ok(bookShelf2);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
		ResponseEntity<?> response;

		try {
			BookShelf bookShelf = bookShelfService.deleteBookShelf(id);
			response = ResponseEntity.ok(bookShelf);
		} catch (Exception e) {
			response = ResponseEntity.badRequest().body(e.getMessage());
		}

		return response;
	}

}
