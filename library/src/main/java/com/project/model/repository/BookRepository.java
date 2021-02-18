package com.project.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

}
