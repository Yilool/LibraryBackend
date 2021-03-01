package com.project.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.entity.BookShelf;

@Repository
public interface BookShelfRepository extends JpaRepository<BookShelf, Integer>{
	public BookShelf findBookShelfByGenre(final String genre);
	public BookShelf findBookShelfById(final Integer id);
}
