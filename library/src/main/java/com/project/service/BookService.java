package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.converter.BookDTOConverter;
import com.project.model.dto.BookDTO;
import com.project.model.entity.Book;
import com.project.model.entity.BookShelf;
import com.project.model.repository.BookRepository;
import com.project.model.repository.BookShelfRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookShelfRepository bookShelfRepository;
	@Autowired
	private BookDTOConverter converter;

	public List<BookDTO> getAllBook() throws Exception {
		List<Book> result = (List<Book>) bookRepository.findAll();

		if (result.size() == 0) {
			throw new Exception("There aren't books");
		}
		
		List<BookDTO> booksDTOs = new ArrayList<>();
		
		result.stream().forEach(b -> {
			BookDTO bookDTO = converter.fromBookToBookDTO(b);
			booksDTOs.add(bookDTO);
		});

		return booksDTOs;
	}

	public BookDTO addBook(BookDTO book) throws Exception {
		Book book2 = bookRepository.findBookByTitle(book.getTitle());

		if (book2 != null) {
			throw new Exception("Book already exists");
		}

		BookShelf bookShelf = bookShelfRepository.findBookShelfByGenre(book.getBookShelf());

		if (bookShelf == null) {
			throw new Exception("The bookshelf doesn't exist");
		}

		book2 = converter.fromBookDTOToBook(book);

		return converter.fromBookToBookDTO(bookRepository.save(book2));
	}

	public BookDTO getBookById(Integer id) throws Exception {
		Book book2 = bookRepository.findBookById(id);

		if (book2 == null) {
			throw new Exception("Book doesn't exist");
		}

		return converter.fromBookToBookDTO(book2);
	}

	public BookDTO updateBook(Integer id, BookDTO book) throws Exception {
		Book book2 = bookRepository.findBookById(id);

		if (book2 == null) {
			throw new Exception("Book doesn't exist");
		}

		book2.setTitle((book.getTitle() == null) ? book2.getTitle() : book.getTitle());
		book2.setPages((book.getPages() == 0) ? book2.getPages() : book.getPages());
		book2.setAuthor((book.getAuthor() == null) ? book2.getAuthor() : book.getAuthor());
		book2.setPublisher((book.getPublisher() == null) ? book2.getPublisher() : book.getPublisher());
		book2.setEdition((book.getEdition() == null) ? book2.getEdition() : book.getEdition());
		book2.setIsbn((book.getIsbn() == null) ? book2.getIsbn() : book.getIsbn());
		book2.setBorrow(book.isBorrow());
		book2.setBookShelf((book.getBookShelf() == null) ? book2.getBookShelf() : bookShelfRepository.findBookShelfByGenre(book.getBookShelf()));
		

		return converter.fromBookToBookDTO(bookRepository.save(book2));
	}

	public BookDTO deleteBook(Integer id) throws Exception {
		Book book = bookRepository.findBookById(id);

		if (book == null) {
			throw new Exception("Book doesn't exist");
		}

		bookRepository.deleteById(id);

		return converter.fromBookToBookDTO(book);
	}
}
