package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.converter.BorrowDTOConverter;
import com.project.model.dto.BorrowDTO;
import com.project.model.entity.Borrow;
import com.project.model.entity.LibraryUser;
import com.project.model.repository.BorrowRepository;

@Service
public class BorrowService {
	private static final int MAX_BORROWS = 3;
	@Autowired
	private BorrowRepository borrowRepository;
	@Autowired
	private BorrowDTOConverter converter;
	
	public void getBorrow(BorrowDTO borrowDTO) throws Exception {
		Borrow borrow = converter.fromBorrowDTOToBorrow(borrowDTO);
		
		List<Borrow> borrows = this.getUserBorrows(borrow.getLibraryUser());
		
		this.isGreaterThanBorrowsLimitException(borrows);
		
		if (this.isGreaterThanBorrowsLimit(borrows)) {
			
		}
	}
	
	private List<Borrow> getUserBorrows(LibraryUser libraryUser) {
		return borrowRepository.findBorrowByLibraryUser(libraryUser);
	}
	
	private Boolean isGreaterThanBorrowsLimit(List<Borrow> borrows) {
		return (borrows.size() > MAX_BORROWS) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	private void isGreaterThanBorrowsLimitException(List<Borrow> borrows) throws Exception{
		if(borrows.size() > MAX_BORROWS) {
			throw new Exception("The user has exceeded the borrow's limit");
		}
	}
}
