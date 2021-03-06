package com.project.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.project.security.model.SecurityUser;

@Entity
public class LibraryUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String surname;
	
	private String dni;
	
	private String birthdate;
	
	private String phonenumber;
	
	@OneToOne(mappedBy = "libraryUser")
	private SecurityUser segurityUser;
	
	public LibraryUser() {
		super();
	}
	
	public LibraryUser(String name, String surname, String dni, String birthdate, String number, SecurityUser segurityUser) {
		super();
		this.name = name;
		this.surname = surname;
		this.dni = dni;
		this.birthdate = birthdate;
		this.phonenumber = number;
		this.segurityUser = segurityUser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getNumber() {
		return phonenumber;
	}

	public void setNumber(String number) {
		this.phonenumber = number;
	}

	public SecurityUser getSegurityUser() {
		return segurityUser;
	}
}
