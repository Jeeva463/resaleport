package com.example.resale.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.resale.entity.User;
import com.example.resale.enums.Status;
import com.example.resale.enums.Usertype;
import com.example.resale.repository.UserRepository;
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
				.age(user.getAge())
				.email(user.getEmail())
				.userName(user.getUsername())
				.dateOfBirth(user.getDateOfBirth())
				.gender(user.getGender())
				.status(Status.ACTIVE)
				.role(Usertype.USER)
				.password(passwordEncoder.encode(user.getPassword()))
				.conformPassword(passwordEncoder.encode(user.getConformPassword()))
				.mobileNo(user.getMobileNo()).build();
				//return user;
		
	//}
		
		return  userRepository.save(userobj);
		
}
}
