package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.entity.BookShelf;
import com.project.model.repository.BookShelfRepository;

@Service
public class BookShelfService {
	@Autowired
	private BookShelfRepository bookShelfRepository;

	public List<BookShelf> getAllBookShelves() throws Exception {
		List<BookShelf> result = (List<BookShelf>) bookShelfRepository.findAll();

		if (result.size() == 0) {
			throw new Exception("There aren't BookShelves");
		}

		return result;
	}

	public BookShelf addBookShelf(BookShelf bookShelf) throws Exception {
		BookShelf bookShelf2 = bookShelfRepository.findBookShelfByGenre(bookShelf.getGenre());

		if (bookShelf2 != null) {
			throw new Exception("BookShelf already exists");
		}

		return bookShelfRepository.save(bookShelf);
	}

	public BookShelf getBookShelfById(Integer id) throws Exception {
		BookShelf bookShelf = bookShelfRepository.findBookShelfById(id);

		if (bookShelf == null) {
			throw new Exception("BookShelf doesn't exist");
		}

		return bookShelf;
	}

	public BookShelf deleteBookShelf(Integer id) throws Exception {
		BookShelf bookShelf = bookShelfRepository.findBookShelfById(id);

		if (bookShelf == null) {
			throw new Exception("BookShelf doesn't exist");
		}
		bookShelfRepository.delete(bookShelf);

		return bookShelf;
	}
}
