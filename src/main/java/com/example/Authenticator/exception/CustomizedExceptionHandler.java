package com.example.Authenticator.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@EnableWebMvc
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(SessionExpiredException.class)
	@ResponseBody
	public final ResponseEntity<Object> handleSessionExpiredException(SessionExpiredException ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse("ERRORCODE01", "SESSION_EXPIRED",
				"Session got expired please retry with fresh token");
		// returning exception structure and Not Found status
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public ResponseEntity<Object> handleResourceNotFound(final ResourceNotFoundException exception,
			final HttpServletRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse("ERRORCODE41", "RESOURCE_NOT_FOUND",
				request.getRequestURI());
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse("ERRORCODE99", "SERVER_ERROR",
				"Something went wrong, Please try again later");
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
