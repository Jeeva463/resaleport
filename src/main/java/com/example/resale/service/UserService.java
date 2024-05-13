package com.example.resale.service;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.resale.entity.User;
import com.example.resale.enums.Status;
import com.example.resale.enums.Usertype;
import com.example.resale.repository.UserRepository;
import com.example.resale.response.MessageService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserService {
	private final  UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	//public User userRegister(User user) {
//		return userRepository.save(user);
		
	//}
	
	
	public  User userRegister(User user) {
		User userobj = User.builder()
				.name(user.getName())
				.Age(user.getAge()).build();
//				.Email(user.getEmail())
//				.userName(user.getUserName())
//				.DOB(user.getDOB())
//				.Gender(user.getGender())
//				.Status(Status.ACTIVE)
//				.Role(Usertype.USER)
//				.Address(user.getAddress())
//				.Password(passwordEncoder.encode(user.getPassword()))
//				.conformPassword(passwordEncoder.encode(user.getConformPassword()))
//				.mobileNo(user.getMobileNo()).build();
//				userRepository.saveAndFlush(user);
//				
//				return user;
//		
//	}
//		
		return  userRepository.save(userobj);
		
}
}
