package com.mwu.withswagger.exception;
public class InvalidInputException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	public InvalidInputException(String message) {
		super(message);
	}

}
