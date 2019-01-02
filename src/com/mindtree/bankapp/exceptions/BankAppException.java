package com.mindtree.bankapp.exceptions;

public class BankAppException extends Exception {

	/**
	 * Main application exceptions
	 */
	private static final long serialVersionUID = 1L;

	public BankAppException() {
		super();
	}

	public BankAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BankAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public BankAppException(String message) {
		super(message);
	}

	public BankAppException(Throwable cause) {
		super(cause);
	}
}