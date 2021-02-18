package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.repository.BookRepository;
import com.project.model.repository.BookShelfRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookShelfRepository bookShelfRepository;
}
