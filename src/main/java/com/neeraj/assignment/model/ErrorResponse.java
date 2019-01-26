package com.neeraj.assignment.model;

import java.time.LocalDateTime;

public class ErrorResponse {
	private int statusCode;
	private String errorMessage;
	private final LocalDateTime timeStamp;

	public ErrorResponse(int status, String errorMessage) {
		this.statusCode = status;
		this.errorMessage = errorMessage;
		this.timeStamp = LocalDateTime.now();
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
}