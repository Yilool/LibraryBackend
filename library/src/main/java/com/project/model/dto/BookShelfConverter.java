package com.project.model.dto;

import org.springframework.stereotype.Component;

import com.project.model.entity.BookShelf;

@Component
public class BookShelfConverter {
	public BookShelf fromBookShelfDTOToBook(BookShelfDTO dto) {
		
		BookShelf bookShelf = new BookShelf(dto.getGenre());
		
		return bookShelf;
	} 

	public BookShelfDTO fromBookToBookShelfDTO(BookShelf bookShelf) {
		BookShelfDTO dto = new BookShelfDTO();

		dto.setGenre(bookShelf.getGenre());

		return dto;
	}
}
