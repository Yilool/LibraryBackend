package com.project.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class BookShelf {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String genre;
	
	@OneToMany(mappedBy="bookShelf", cascade = CascadeType.ALL)
	private List<Book> books;

	public BookShelf() {
		super();
		this.books = new ArrayList<>();
	}
	
	public BookShelf(String genre) {
		super();
		this.genre = genre.substring(0, 1).toUpperCase() + genre.substring(1).toLowerCase();
		this.books = new ArrayList<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
