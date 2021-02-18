package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.repository.BookShelfRepository;

@Service
public class BookShelfService {
	@Autowired
	private BookShelfRepository bookShelfRepository;
}
