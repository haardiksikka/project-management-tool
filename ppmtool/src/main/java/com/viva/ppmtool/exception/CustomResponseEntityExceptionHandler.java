package com.viva.ppmtool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//if exception occurs in controller then controle will come to this
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	public final ResponseEntity<Object> handleProjectIdException(ProjectIdException ex, WebRequest req){
		ProjectIdExceptionResponse exception = new ProjectIdExceptionResponse(ex.getMessage());
		return new ResponseEntity(exception, HttpStatus.BAD_REQUEST);
	}
	
	 @ExceptionHandler
	    public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException ex, WebRequest request){
	        ProjectNotFoundExceptionResponse exceptionResponse = new ProjectNotFoundExceptionResponse(ex.getMessage());
	        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	    }

}
