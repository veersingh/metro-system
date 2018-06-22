package com.sapient.metrosystem.exception;

public class InsuffficentBalanceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1497256166040552243L;

	public InsuffficentBalanceException() {
		super();

	}

	public InsuffficentBalanceException(String message, Throwable cause) {
		super(message, cause);

	}

	public InsuffficentBalanceException(String message) {
		super(message);

	}

	public InsuffficentBalanceException(Throwable cause) {
		super(cause);

	}

}
