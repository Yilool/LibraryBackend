package com.project.model.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Borrow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@CreatedDate
	private LocalDate createDate;
	
	private LocalDate deliveryDate;
	
	private LocalDate deleteDate;
	
	int numExtend;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "libraryuser_id", referencedColumnName = "id", nullable = false)
	private LibraryUser libraryUser;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
	private Book book;

	public Borrow() {
		super();
		this.deleteDate = null;
		this.numExtend = 0;
		this.createDate = LocalDate.now();
		this.deliveryDate = LocalDate.now().plusDays(15);
	}
	
	public Borrow(LibraryUser libraryUser, Book book) {
		super();
		this.deleteDate = null;
		this.numExtend = 0;
		this.createDate = LocalDate.now();
		this.deliveryDate = LocalDate.now().plusDays(15);
		this.libraryUser = libraryUser;
		this.book = book;
	}

	
	public LocalDate getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(LocalDate deleteTime) {
		this.deleteDate = deleteTime;
	}

	public int getNumExtend() {
		return numExtend;
	}

	public void setNumExtend(int numExtend) {
		this.numExtend = numExtend;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createTime) {
		this.createDate = createTime;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public LibraryUser getLibraryUser() {
		return libraryUser;
	}

	public void setLibraryUser(LibraryUser libraryUser) {
		this.libraryUser = libraryUser;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
