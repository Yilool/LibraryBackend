package com.project.exception;

import com.project.model.entity.ErrorCode;

public class BorrowException extends Exception{
	private ErrorCode code;
	private String msg;
	
	public BorrowException() {
		
	}
}
