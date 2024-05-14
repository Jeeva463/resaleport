package com.example.resale.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.security.auth.Subject;

import org.springframework.stereotype.Service;


import com.example.resale.entity.User;
import com.example.resale.enums.RequestType;
import com.example.resale.exception.ObjectInvalidException;
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
	private final MessageService messageService;
	private final @NonNull UserService userService;
	private final @NonNull UserRepository userRepository;

	List<String> errors = null;
	Optional<Subject> subject = null;
	//List<String> errorsObj = null;
	public ValidationResult validate(RequestType requesttype, UserRegister request) throws ObjectInvalidException  {
		ArrayList<String>errors = new ArrayList<>();
		User user = null;
		
		if(!ValidationUtil.isNameValid(request.getName())) {// !isNameValid-->!=
			errors.add(messageService.messageResponse("ValidatorService.user.name.required"));
		}else {
			request.setName((ValidationUtil.getFormattedString(request.getName())));
			if(!ValidationUtil.isValidName(request.getName())) {
				errors.add(messageService.messageResponse("ValidatorService.user.name.invalid"));
			}
		}
		
		
		if(ValidationUtil.isAgeRequired(request.getAge())) {
			errors.add(messageService.messageResponse("ValidatorService.age.required"));
		}else {
			if(!ValidationUtil.isEighteenOrOlder(request.getDateOfBirth(),request.getAge())) {
				errors.add(messageService.messageResponse("ValidatorService.age.invalid"));
			}
			if(!ValidationUtil.isDateOfBirthAgeMatcher(request.getDateOfBirth(), request.getAge())) {
				errors.add(messageService.messageResponse("ValidatorService.age.matcher"));
			}
		}
		
		
		
		if(ValidationUtil.isnullorEmpty(request.getUserName())) {
			errors.add(messageService.messageResponse("ValidatorService.user.name.required"));
		}else {
			request.setUserName(ValidationUtil.getFormattedString(request.getUserName()));
			if((!ValidationUtil.isUserNameValid(request.getUserName()))){
				errors.add(messageService.messageResponse("ValidatorService.user.name.invalid"));
			}
			}
		
		if(ValidationUtil.isnullorEmpty(ValidationUtil.getFormattedString(request.getEmail()))) {
			errors.add(messageService.messageResponse("ValidatorService.email.required"));
		}else {
			request.setEmail(ValidationUtil.getFormattedString(request.getEmail()));
			if(!ValidationUtil.isValidEmailId(request.getEmail())) {
				errors.add(messageService.messageResponse("ValidatorService.email.invalid"));
			}
		}

		if (ValidationUtil.isnullorEmpty(request.getMobileNo())) {
			errors.add(messageService.messageResponse("ValidatorService.mobile.required"));
		}else {
			request.setMobileNo(ValidationUtil.getFormattedString(request.getMobileNo()));
			if(!ValidationUtil.isValidMobileNumber(request.getMobileNo())) {
				errors.add(messageService.messageResponse("ValidatorService.mobile.invalid"));
			}
		}
		

		if(ValidationUtil.isDateOfBirthrequired(request.getDateOfBirth())) {
			errors.add(messageService.messageResponse("ValidatorService.date.of.birth.required"));
		}else {
			if(!ValidationUtil.isDateOfBirthValid(request.getDateOfBirth())) {
				errors.add(messageService.messageResponse("ValidatorService.date.of.birth.invalid"));
			}
		}
		

		if(ValidationUtil.isGenderrequired(request.getGender())) {
			errors.add(messageService.messageResponse("ValidatorService.gender.required"));
		}else {
			request.setGender(ValidationUtil.getFormattedGender(request.getGender()));
			if(!ValidationUtil.isGenderValid(request.getGender())) {
				errors.add(messageService.messageResponse("ValidatorService.gender.invalid"));
			}
		}
		

		if(ValidationUtil.isnullorEmpty(request.getPassword())) {
			errors.add(messageService.messageResponse("ValidatorService.password.required"));
		}else {
			request.setPassword(ValidationUtil.getFormattedString(request.getPassword()));
			if(!ValidationUtil.isPasswordValid(request.getPassword(), request.getConformPassword())) {
				errors.add(messageService.messageResponse("ValidatorService.password.invalid"));
			}
		}

		ValidationResult result = new ValidationResult();
		if(errors.size()>0) {
			String errorMessage = errors.stream().map(a -> String.valueOf(a)).collect(Collectors.joining(", "));
			throw new ObjectInvalidException(errorMessage);
		}
		user = User.builder()
				.name(request.getName())
     			.Age(request.getAge())
				.Email(request.getEmail())
				.Password(request.getPassword())
				.conformPassword(request.getConformPassword())
				.Address(request.getAddress())
				.mobileNo(request.getMobileNo())
				.DateOfBirth(request.getDateOfBirth())
				.Gender(request.getGender()).build();
		
		result.setObject(user);
		return result;
		
		//return validationResult;
		
	}
}
