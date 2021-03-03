package com.project.model.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.model.entity.Book;
import com.project.model.entity.BookShelf;
import com.project.model.repository.BookShelfRepository;

@Component
public class BookDTOConverter {
	@Autowired
	private BookShelfRepository bookShelfRepository;

	public Book fromBookDTOToBook(BookDTO dto) {
		BookShelf bookShelf = bookShelfRepository.findBookShelfByGenre(dto.getBookShelf());
		
		Book book = new Book(dto.getTitle(), dto.getAuthor(), dto.getPublisher(), dto.getEdition(), dto.getIsbn(), dto.getPages());

		book.setBookShelf(bookShelf);
		book.setBorrow(dto.isBorrow());
		
		return book;
	} 

	public BookDTO fromBookToBookDTO(Book book) {
		BookDTO dto = new BookDTO();

		dto.setId(book.getId());
		dto.setTitle(book.getTitle());
		dto.setAuthor(book.getAuthor());
		dto.setPublisher(book.getPublisher());
		dto.setEdition(book.getEdition());
		dto.setIsbn(book.getIsbn());
		dto.setPages(book.getPages());
		dto.setBookShelf(book.getBookShelf().getGenre());

		return dto;
	}	
}
