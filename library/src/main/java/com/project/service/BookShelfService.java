package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.converter.BookShelfDTOConverter;
import com.project.model.dto.BookShelfDTO;
import com.project.model.entity.BookShelf;
import com.project.model.repository.BookShelfRepository;

@Service
public class BookShelfService {
	@Autowired
	private BookShelfRepository bookShelfRepository;
	@Autowired
	private BookShelfDTOConverter converter;
	
	public List<BookShelfDTO> getAllBookShelves() throws Exception {
		List<BookShelf> result = (List<BookShelf>) bookShelfRepository.findAll();

		if (result.size() == 0) {
			throw new Exception("There aren't BookShelves");
		}

		List<BookShelfDTO> bookShelfsDTOs = new ArrayList<>();
		
		result.stream().forEach(bs -> {
			BookShelfDTO bookShelfDTO = converter.fromBookToBookShelfDTO(bs);
			bookShelfsDTOs.add(bookShelfDTO);
		});
		
		return bookShelfsDTOs;
	}

	public BookShelfDTO addBookShelf(BookShelfDTO bookShelf) throws Exception {
		BookShelf bookShelf2 = bookShelfRepository.findBookShelfByGenre(bookShelf.getGenre());

		if (bookShelf2 != null) {
			throw new Exception("BookShelf already exists");
		}
		
		bookShelf2 = converter.fromBookShelfDTOToBook(bookShelf);

		return converter.fromBookToBookShelfDTO(bookShelfRepository.save(bookShelf2));
	}

	public BookShelfDTO getBookShelfById(Integer id) throws Exception {
		BookShelf bookShelf = bookShelfRepository.findBookShelfById(id);

		if (bookShelf == null) {
			throw new Exception("BookShelf doesn't exist");
		}

		return converter.fromBookToBookShelfDTO(bookShelf);
	}

	public BookShelfDTO deleteBookShelf(Integer id) throws Exception {
		BookShelf bookShelf = bookShelfRepository.findBookShelfById(id);

		if (bookShelf == null) {
			throw new Exception("BookShelf doesn't exist");
		}
		
		bookShelfRepository.delete(bookShelf);

		return converter.fromBookToBookShelfDTO(bookShelf);
	}
}
