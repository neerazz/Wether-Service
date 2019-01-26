package com.neeraj.assignment.exception;

public class InvalidZipCode extends RuntimeException {
	public InvalidZipCode(String message) {
		super(message);
	}
}