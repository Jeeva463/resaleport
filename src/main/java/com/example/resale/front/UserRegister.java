package com.example.resale.front;

import com.example.resale.enums.GenderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegister {
private String userName;
	
	private String name;
	
	private String Age;
	
	private String email;
	
	private String mobileNo;
	
	private GenderType gender;
	
	private String password;
	
	private String conformPassword;
	
	private String dateOfBirth;
	
}
