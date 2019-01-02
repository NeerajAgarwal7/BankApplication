package com.mindtree.bankapp.exceptions.daoExceptions;

import com.mindtree.bankapp.exceptions.BankAppException;

public class BankDaoException extends BankAppException{

	/**
	 * Bank application exceptions
	 */
	private static final long serialVersionUID = 1L;

	public BankDaoException() {
		super();
	}

	public BankDaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BankDaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public BankDaoException(String message) {
		super(message);
	}

	public BankDaoException(Throwable cause) {
		super(cause);
	}

	
	
}
