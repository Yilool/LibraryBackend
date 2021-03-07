package com.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.model.converter.BorrowDTOConverter;
import com.project.model.dto.BorrowDTO;
import com.project.model.entity.Borrow;
import com.project.model.entity.LibraryUser;
import com.project.model.repository.BorrowRepository;
import com.project.security.model.SecurityUser;
import com.project.security.model.repository.SecurityUserRepository;

@Service
public class BorrowService {
	private static final int MAX_BORROWS = 3;
	@Autowired
	private BorrowRepository borrowRepository;
	@Autowired
	private SecurityUserRepository securityUserRepository;
	@Autowired
	private BorrowDTOConverter converter;

	public List<BorrowDTO> getUserBorrowsByUsername(String username) throws Exception {
		List<BorrowDTO> borrowDTOs = new ArrayList<>();
		SecurityUser su = securityUserRepository.findSegurityUserByUsername(username);

		if (su == null) {
			throw new Exception("Username doesn't exist");
		}
		
		List<Borrow> borrows = this.getUserBorrowsByLibraryUser(su.getLibraryUser());
		
		borrows.stream().forEach(b -> {
			BorrowDTO borrowDTO = converter.fromBorrowToBorrowDTO(b);
			
			borrowDTOs.add(borrowDTO);
		});
		
		return borrowDTOs;
	}

	public BorrowDTO deleteBorrow(BorrowDTO borrowDTO) throws Exception {
		Borrow borrow = borrowRepository.findBorrowById(borrowDTO.getId());

		if (borrow == null) {
			throw new Exception(
					"The user " + borrowDTO.getUsername() + " haven't borrow the book " + borrowDTO.getTitle());
		}

		borrowRepository.deleteById(borrow.getId());

		return converter.fromBorrowToBorrowDTO(borrow);
	}

	public BorrowDTO extendDeliveryDate(BorrowDTO borrowDTO) throws Exception {
		Borrow borrow = borrowRepository.findBorrowById(borrowDTO.getId());

		if (borrow == null) {
			throw new Exception(
					"The user " + borrowDTO.getUsername() + " haven't borrow the book " + borrowDTO.getTitle());
		}

		borrow.setDeliveryDate(borrow.getDeliveryDate().plusDays(15));

		return converter.fromBorrowToBorrowDTO(borrowRepository.save(borrow));
	}

	public BorrowDTO newBorrow(BorrowDTO borrowDTO) throws Exception {
		Borrow borrow = converter.fromBorrowDTOToBorrow(borrowDTO);

		List<Borrow> borrows = this.getUserBorrowsByLibraryUser(borrow.getLibraryUser());

		if (this.isGreaterThanBorrowsLimit(borrows)) {
			this.isGreaterThanBorrowsLimitException();
		}

		this.deliveryDateCheck(borrows);

		return converter.fromBorrowToBorrowDTO(borrowRepository.save(borrow));
	}

	/////

	private void deliveryDateCheck(List<Borrow> borrows) throws Exception {
		for (Borrow borrow : borrows) {
			if (this.isDeliveryDatePassed(borrow)) {
				this.isDeliveryDatePassedException(borrow);
			}
		}
	}

	private Boolean isDeliveryDatePassed(Borrow borrow) {
		return (borrow.getDeliveryDate().isBefore(LocalDateTime.now())) ? Boolean.TRUE : Boolean.FALSE;
	}

	private void isDeliveryDatePassedException(Borrow borrow) throws Exception {
		throw new Exception("The delivery date has expired for the book " + borrow.getBook().getTitle());
	}

	private Boolean isGreaterThanBorrowsLimit(List<Borrow> borrows) {
		return (borrows.size() > MAX_BORROWS) ? Boolean.TRUE : Boolean.FALSE;
	}

	private void isGreaterThanBorrowsLimitException() throws Exception {
		throw new Exception("The user has exceeded the borrow's limit");
	}

	private List<Borrow> getUserBorrowsByLibraryUser(LibraryUser libraryUser) {
		return borrowRepository.findBorrowByLibraryUser(libraryUser);
	}
}
