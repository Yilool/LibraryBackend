package com.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.repository.LibraryUserRepository;

@Service
public class LibraryUserService {
	@Autowired
	private LibraryUserRepository libraryUserRepository;
}
