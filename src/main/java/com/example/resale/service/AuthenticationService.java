package com.example.resale.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.resale.dto.ErrorDto;
import com.example.resale.entity.Login;
import com.example.resale.entity.User;
import com.example.resale.repository.UserRepository;
import com.example.resale.security.JwtService;
import com.example.resale.util.PasswordUtil;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
	private final UserRepository userRepository;
	private final JwtService jwtService;

	public ResponseEntity<?> loginUser(Login login) {
		ErrorDto errorDto = null;
		HashMap<String, Object> response = new HashMap<String, Object>();
		if (login == null) {
			errorDto = new ErrorDto();
			errorDto.setCode("400");
			errorDto.setMessage("Invalid");
			response.put("Status", 0);
			response.put("error", errorDto);
			return ResponseEntity.badRequest().body(response);
		}

		Optional<User> obj = userRepository.findByUserName(login.getUserName());

		if (!obj.isPresent()) {
			errorDto = new ErrorDto();
			errorDto.setCode("400");
			errorDto.setMessage("Usernot found.!");
			response.put("status", 0);
			response.put("error", errorDto);
			return ResponseEntity.badRequest().body(response);
		}
		User user = obj.get();
		String enPassword = PasswordUtil.getEncryptedPassword(login.getPassword());
		if (!user.getUsername().equals(login.getUserName())) {
			errorDto = new ErrorDto();
			errorDto.setCode("400");
			errorDto.setMessage("Invalid username.!");
			response.put("status", 0);
			response.put("error", errorDto);
			return ResponseEntity.badRequest().body(response);
		}
		if (!user.getPassword().equals(enPassword)) {
			errorDto = new ErrorDto();
			errorDto.setCode("400");
			errorDto.setMessage("Password is wrong");
			response.put("Status", "0");
			response.put("error", errorDto);

			return ResponseEntity.badRequest().body(response);
		}
		final String token = jwtService.generateToken(user);
		response.put("Status", 1);
		response.put("message", "Logged in successfully.!");
		response.put("jwt", token);
		response.put("userId", user.getId());
		response.put("role", user.getRole());

		return ResponseEntity.ok().body(response);

	}
}
