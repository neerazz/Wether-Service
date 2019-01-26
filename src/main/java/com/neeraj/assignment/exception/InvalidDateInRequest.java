package com.neeraj.assignment.exception;

public class InvalidDateInRequest extends RuntimeException {
	public InvalidDateInRequest(String message) {
		super(message);
	}
}