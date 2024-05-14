package com.example.resale.exception;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import com.example.resale.response.Error;
import com.example.resale.response.Response;


@ControllerAdvice
public class CustomExceptionHandler {
	protected ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Response response = new Response();
		response.setData("Validation Failed");
		Error err = new Error();
		err.setCode(HttpStatus.BAD_REQUEST.toString());
		err.setReason("Validation Failed");
		response.setError(err);
		List<String> errors = new ArrayList<>();
		BindingResult bindingResult = ex.getBindingResult();
		bindingResult.getAllErrors().forEach(error -> errors.add(error.getCode()));
		response.setErrorMessages(errors);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ObjectInvalidException.class)
	public ResponseEntity<?>handleInvalidObjectException(Exception ex, WebRequest request){
		Error error = new Error();
		//Error errors = new Error();
		error.setErrorList((Stream.of(ex.getMessage().split(",")).collect(Collectors.toList())));
		error.setReason(ex.getMessage());
		error.setCode(HttpStatus.BAD_REQUEST.toString());
		Response response = new Response();
		response.setError(error);
		response.setTimeStamp(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		
	}

}
