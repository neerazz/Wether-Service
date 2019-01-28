package com.neeraj.assignment.exception;

public class InvalidDateInRequestException extends RuntimeException {
	public InvalidDateInRequestException(String message) {
		super(message);
	}
}