package com.project.model.converter;

import org.springframework.stereotype.Component;

import com.project.model.dto.BookShelfDTO;
import com.project.model.entity.BookShelf;

@Component
public class BookShelfDTOConverter {
	public BookShelf fromBookShelfDTOToBook(BookShelfDTO dto) {
		
		BookShelf bookShelf = new BookShelf(dto.getGenre());
		
		return bookShelf;
	} 

	public BookShelfDTO fromBookToBookShelfDTO(BookShelf bookShelf) {
		BookShelfDTO dto = new BookShelfDTO();

		dto.setId(bookShelf.getId());
		dto.setGenre(bookShelf.getGenre());

		return dto;
	}
}
