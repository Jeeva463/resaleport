package com.example.resale.controller;

import java.net.http.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.resale.entity.User;
import com.example.resale.enums.RequestType;
import com.example.resale.front.UserRegister;
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
	
	@PostMapping("/post")
	public User userRegister(@RequestBody UserRegister request
			) throws Exception{
		ValidationResult validationResult=validatorService.validate(RequestType.POST,request);
		User user= (User) validationResult.getObject();
		return userService.userRegister(user);
		
	}
}