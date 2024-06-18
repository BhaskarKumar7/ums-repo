package com.makershark.ums.exception;

public class UserCreationFailureException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserCreationFailureException (String message) {
		super(message);
	}
}
