package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<Book> getAllBook() throws Exception {
		List<Book> result = (List<Book>) bookRepository.findAll();

		if (result.size() == 0) {
			throw new Exception("There aren't books");
		}

		return result;
	}

	public Book addBook(Book book) throws Exception {
		Book book2 = bookRepository.findBookByTitle(book.getTitle());

		if (book2 != null) {
			throw new Exception("Book already exists");
		}

		BookShelf bookShelf = bookShelfRepository.findBookShelfById(book.getBookShelf().getId());

		if (bookShelf == null) {
			throw new Exception("The bookshelf doesn't exist");
		}

		book.setBookShelf(bookShelf);

		bookRepository.save(book);

		return book;
	}

	public Book getBookById(Integer id) throws Exception {
		Book book2 = bookRepository.findBookById(id);

		if (book2 == null) {
			throw new Exception("Book doesn't exist");
		}

		return book2;
	}

	public Book updateBook(Integer id, Book book) throws Exception {
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

		bookRepository.save(book2);

		return book2;
	}

	public Book deleteBook(Integer id) throws Exception {
		Book book = bookRepository.findBookById(id);

		if (book == null) {
			throw new Exception("Book doesn't exist");
		}

		bookRepository.deleteById(id);

		return book;
	}
}
