package com.ajeet.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(PoliceException.class)
	public ResponseEntity<ErrorDetails> customerException(PoliceException e, WebRequest req){
		ErrorDetails err= new ErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<ErrorDetails> adminException(AdminException e, WebRequest req){
		ErrorDetails err= new ErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CrimeException.class)
	public ResponseEntity<ErrorDetails> crimeException(CrimeException e, WebRequest req){
		ErrorDetails err= new ErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CriminalException.class)
	public ResponseEntity<ErrorDetails> criminalException(CriminalException e, WebRequest req){
		ErrorDetails err= new ErrorDetails(LocalDateTime.now(), e.getMessage(), req.getDescription(false));
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArgNotValidException(MethodArgumentNotValidException e){
		ErrorDetails err= new ErrorDetails(LocalDateTime.now(), "Validation error", e.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> myExceptionHandler1(IllegalArgumentException e){
		ResponseEntity<String> rs= new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		return rs;
	}
	
	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler2(InvalidIdException e, WebRequest r){
		ErrorDetails err= new ErrorDetails(LocalDateTime.now(), e.getMessage(),r.getDescription(false));
		
		
		
		ResponseEntity<ErrorDetails> rs= new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> myExceptionHandler3(Exception e){
		ResponseEntity<String> rs= new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		return rs;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler4(NoHandlerFoundException e, WebRequest r){
		ErrorDetails err= new ErrorDetails(LocalDateTime.now(), e.getMessage(),r.getDescription(false));
		
		
		
		ResponseEntity<ErrorDetails> rs= new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler5(IllegalStateException e, WebRequest r){
		ErrorDetails err= new ErrorDetails(LocalDateTime.now(), e.getMessage(),r.getDescription(false));
		
		
		
		ResponseEntity<ErrorDetails> rs= new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);
		return rs;
	}

}
