package com.project.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String title;
	
	private int pages;
	
	private String author;
	
	private String publisher;
	
	private String edition;
	
	private String isbn;
	
	private boolean borrow;
	
	@ManyToOne
	@JoinColumn(name = "bookShelf", foreignKey = @ForeignKey(name = "fk_book_id"))
	private BookShelf bookShelf;

	public Book() {
		super();
		this.borrow = Boolean.FALSE;
	}
	
	public Book(String title, String author, String publisher, String edition, String isbn, int pages) {
		super();
		this.borrow = Boolean.FALSE;
		this.title = title.substring(0, 1) + title.substring(1);
		this.author = author;
		this.publisher = publisher;
		this.edition = edition;
		this.isbn = isbn;
		this.pages = pages;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getEdition() {
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public boolean isBorrow() {
		return borrow;
	}

	public void setBorrow(boolean borrow) {
		this.borrow = borrow;
	}

	public BookShelf getBookShelf() {
		return bookShelf;
	}

	public void setBookShelf(BookShelf bookShelf) {
		this.bookShelf = bookShelf;
	}
}
