package com.neeraj.assignment.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.neeraj.assignment.model.ErrorResponse;

@ControllerAdvice("com.neeraj.assignment")
public class GlobalException {

	@ExceptionHandler(InvalidDateInRequestException.class)
	public ResponseEntity<ErrorResponse> handleException(InvalidDateInRequestException exc) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidSecreatKeyException.class)
	public ResponseEntity<ErrorResponse> handleException(InvalidSecreatKeyException exc) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidZipCodeException.class)
	public ResponseEntity<ErrorResponse> handleException(InvalidZipCodeException exc) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ InvalidTokenException.class })
	public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(InvalidTokenException exc) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), exc.getMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(TypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleTypeMismatchException(HttpServletRequest req, TypeMismatchException ex) {
		String errorMessage = "URL:" + req.getRequestURL().toString()
				+ " is invalid. Kindly check and provide correct Zip Code and days in URL.";
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), errorMessage);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(HttpServletRequest req,
			NoHandlerFoundException ex) {
		String errorMessage = "Specified path :" + req.getRequestURL().toString()
				+ " is invalid. Kindly check and provide correct URL.";
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), errorMessage);
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}
}