package com.project.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.model.entity.Borrow;
import com.project.model.entity.LibraryUser;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Integer>{
	public Borrow findBorrowById(final Integer id);
	public List<Borrow> findBorrowByLibraryUser(final LibraryUser libraryUser);
}
