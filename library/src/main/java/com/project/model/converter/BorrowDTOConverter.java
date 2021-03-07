package com.project.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.model.dto.BorrowDTO;
import com.project.model.entity.Book;
import com.project.model.entity.Borrow;
import com.project.model.entity.LibraryUser;
import com.project.model.repository.BookRepository;
import com.project.security.model.SecurityUser;
import com.project.security.model.repository.SecurityUserRepository;

@Component
public class BorrowDTOConverter {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private SecurityUserRepository segurityUserRepository;
	
	public Borrow fromBorrowDTOToBorrow(BorrowDTO borrow) throws Exception {
		SecurityUser su = segurityUserRepository.findSegurityUserByUsername(borrow.getUsername());
		
		if (su == null) {
			throw new Exception("Username doesn't exist");
		}
		
		LibraryUser lu = su.getLibraryUser();
		
		if (lu  == null) {
			throw new Exception("User isn't register");
		}
		
		Book b = bookRepository.findBookByTitle(borrow.getTitle());
		
		if (b == null) {
			throw new Exception("Book isn't found");
		}
		
		Borrow borrow2 = new Borrow(lu, b);
		
		return borrow2;
	}
	
	public BorrowDTO fromBorrowToBorrowDTO(Borrow borrow) {
		SecurityUser su = borrow.getLibraryUser().getSegurityUser();
		Book b = borrow.getBook();
		
		BorrowDTO borrowDTO = new BorrowDTO();
		
		borrowDTO.setUsername(su.getUsername());
		borrowDTO.setTitle(b.getTitle());
		
		return borrowDTO;
	}
}
