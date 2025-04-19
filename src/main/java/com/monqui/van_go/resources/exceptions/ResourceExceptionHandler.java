package com.monqui.van_go.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.monqui.van_go.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException rnf, HttpServletRequest request){
		
		String errorMessage = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError error = new StandardError(Instant.now(), status.value(), errorMessage, rnf.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(error);
	}
}
