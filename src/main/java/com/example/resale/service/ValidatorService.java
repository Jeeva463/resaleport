package com.example.resale.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;


import com.example.resale.entity.User;
import com.example.resale.enums.RequestType;
import com.example.resale.front.UserRegister;
import com.example.resale.repository.UserRepository;
import com.example.resale.response.MessageService;
import com.example.resale.util.ValidationUtil;
import com.example.resale.validator.ValidationResult;

import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidatorService {
	ValidationResult validationResult;
	
	private final MessageService messageService;
	private final @NonNull UserService userService;
	private final @NonNull UserRepository userRepository;

	List<String> errors = null;
	//List<String> errorsObj = null;
	public ValidationResult validate(RequestType requesttype, UserRegister request) throws Exception {
		errors = new ArrayList<>();
		User user1 = null;
		
		if(!ValidationUtil.isNameValid(request.getName())) {
			errors.add(messageService.messageResponse("ValidatorService.user.name.required"));
		}else {
			request.setName((ValidationUtil.getFormattedString(request.getName())));
			if(!ValidationUtil.isValidName(request.getName())) {
				errors.add(messageService.messageResponse("ValidatorService.user.name.invalid"));
			}
		}
		ValidationResult result = new ValidationResult();
		if(errors.size()>0) {
			String errorMessage = errors.stream().map(a -> String.valueOf(a)).collect(Collectors.joining(", "));
			throw new Exception(errorMessage);
		}
		user1 = User.builder()
				.name(request.getName()).build();
//				.name(request.getName())
//				.age(request.getAge())
//				.email(request.getEmail())
//				.password(enPassword)
//				.confirmpassword(enPassword1)
//				.location(request.getLocation())
//				.phoneNumber(request.getPhoneNumber())
//				.dateOfBirth(request.getDateOfBirth())
//				.gender(request.getGender()).build();
		
		result.setObject(user1);
		return result;
//		
//		
//		if(ValidationUtil.isnullorEmpty(request.getUserName())) {
//			errors.add(messageService.messageResponse("ValidatorService.user.name.required"));
//		}else {
//			request.setUserName(ValidationUtil.getFormattedString(request.getUserName()));
//			if((!ValidationUtil.isUserNameValid(request.getUserName()))){
//				errors.add(messageService.messageResponse("ValidatorService.user.name.invalid"));
//			}
//			}
//		
//		if(ValidationUtil.isnullorEmpty(ValidationUtil.getFormattedString(request.getEmail()))) {
//			errors.add(messageService.messageResponse("ValidatorService.email.required"));
//		}else {
//			request.setEmail(ValidationUtil.getFormattedString(request.getEmail()));
//			if(!ValidationUtil.isValidEmailId(request.getEmail())) {
//				errors.add(messageService.messageResponse("ValidatorService.email.invalid"));
//			}
//		}
//
//		if (ValidationUtil.isnullorEmpty(request.getMobileNo())) {
//			errors.add(messageService.messageResponse("ValidatorService.mobile.required"));
//		}else {
//			request.setMobileNo(ValidationUtil.getFormattedString(request.getMobileNo()));
//			if(!ValidationUtil.isValidMobileNumber(request.getMobileNo())) {
//				errors.add(messageService.messageResponse("ValidatorService.mobile.invalid"));
//			}
//		}
//		
//
//		if(ValidationUtil.isDateOfBirthrequired(request.getDOB())) {
//			errors.add(messageService.messageResponse("ValidatorService.date.of.birth.required"));
//		}else {
//			if(!ValidationUtil.isDateOfBirthValid(request.getDOB())) {
//				errors.add(messageService.messageResponse("ValidatorService.date.of.birth.invalid"));
//			}
//		}
//		
//
//		if(ValidationUtil.isGenderrequired(request.getGender())) {
//			errors.add(messageService.messageResponse("ValidatorService.gender.required"));
//		}else {
//			request.setGender(ValidationUtil.getFormattedGender(request.getGender()));
//			if(!ValidationUtil.isGenderValid(request.getGender())) {
//				errors.add(messageService.messageResponse("ValidatorService.gender.invalid"));
//			}
//		}
//		
//
//		if(ValidationUtil.isnullorEmpty(request.getPassword())) {
//			errors.add(messageService.messageResponse("ValidatorService.password.required"));
//		}else {
//			request.setPassword(ValidationUtil.getFormattedString(request.getPassword()));
//			if(!ValidationUtil.isPasswordValid(request.getPassword(), request.getConformPassword())) {
//				errors.add(messageService.messageResponse("ValidatorService.password.invalid"));
//			}
//		}
//		
//		return validationResult;
		
	}
}
