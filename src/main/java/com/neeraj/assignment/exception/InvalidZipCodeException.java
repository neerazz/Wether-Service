package com.neeraj.assignment.exception;

public class InvalidZipCodeException extends RuntimeException {

	private static final long serialVersionUID = 8014215437072646083L;

	public InvalidZipCodeException(String message) {
		super(message);
	}
}