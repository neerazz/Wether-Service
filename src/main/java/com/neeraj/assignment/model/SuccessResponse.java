package com.neeraj.assignment.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class SuccessResponse {

	private HttpStatus statusCode;
	private String message;
	private final LocalDateTime timeStamp;
	private WeatherEntry responseData;

	public SuccessResponse() {
		this.timeStamp = LocalDateTime.now();
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public WeatherEntry getResponseData() {
		return responseData;
	}

	public void setResponseData(WeatherEntry responseData) {
		this.responseData = responseData;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
}
