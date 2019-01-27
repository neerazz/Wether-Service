package com.neeraj.assignment.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.neeraj.assignment.model.ErrorResponse;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(InvalidDateInRequest.class)
	public ResponseEntity<ErrorResponse> handleException(InvalidDateInRequest exc) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidZipCode.class)
	public ResponseEntity<ErrorResponse> handleException(InvalidZipCode exc) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleException(RuntimeException exc) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exc.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponse> handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
		String errorMessage = "URL:" + req.getRequestURL().toString()
				+ " is invalid. Kindly check and provide correct Zip Code and days in URL.";
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), errorMessage);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(HttpServletRequest req,
			NoHandlerFoundException ex) {
		String errorMessage = "Specified path :" + req.getRequestURL().toString()
				+ " is invalid. Kindly check and provide correct URL.";
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), errorMessage);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}
}