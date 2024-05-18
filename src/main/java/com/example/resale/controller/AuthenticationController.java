package com.example.resale.controller;


import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

import com.example.resale.entity.Login;
import com.example.resale.entity.User;
import com.example.resale.enums.RequestType;
import com.example.resale.front.UserRegister;
import com.example.resale.response.ResponseGenerator;
import com.example.resale.response.TransactionContext;
import com.example.resale.security.JwtService;
import com.example.resale.service.AuthenticationService;
import com.example.resale.service.UserService;
import com.example.resale.service.ValidatorService;
import com.example.resale.validator.ValidationResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user/register")
@RequiredArgsConstructor
public class AuthenticationController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final UserService userService;
	private final ValidatorService validatorService;
	private final JwtService jwtService;
	private final ResponseGenerator responseGenerator;
	private final AuthenticationService authenticationService;
	
	@PostMapping("/post")
	public ResponseEntity<?> userRegister(@RequestBody UserRegister request,@RequestHeader HttpHeaders httpHeaders
			) throws Exception{
		ValidationResult validationResult=validatorService.validate(RequestType.POST,request);
		User user= (User) validationResult.getObject();
		User user1 = userService.userRegister(user);
		
		Map<String, Object> response = new HashMap<>();
		final String token = jwtService.generateToken(user1);
		response.put("Status", 1);// indha 3-m ok statusna ipdi vara eludhunadhu
		response.put("message", "You have registerd successfully.");
		response.put("token",token);
		
		TransactionContext context = responseGenerator.generatorTransactioncontext(httpHeaders);
		try {
		return responseGenerator.successResponse(context, response, HttpStatus.OK);
		}
		catch(Exception e){	
			e.printStackTrace();//printStackTrace edhu varugira problemai listout(print) panni kattum
			logger.error(e.getMessage(), e);
		return responseGenerator.errorResponse(context, e.getMessage(), HttpStatus.BAD_REQUEST);
		}	
	}
	@PostMapping("/userlogin")
	public ResponseEntity<?> loginUser(@RequestBody Login login,@RequestBody HttpHeaders httpHeaders  ){
		ResponseEntity<?> authenticationservice =	authenticationService.loginUser(login);
		TransactionContext context = responseGenerator.generatorTransactioncontext(httpHeaders);
		try {
			return responseGenerator.successResponse(context, authenticationservice, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return responseGenerator.errorResponse(context, e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
}
