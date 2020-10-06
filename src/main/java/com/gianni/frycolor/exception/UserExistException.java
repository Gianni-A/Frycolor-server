package com.gianni.frycolor.exception;

public class UserExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public UserExistException(String s) {
		super(s);
	}

}
