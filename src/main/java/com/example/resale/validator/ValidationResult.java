package com.example.resale.validator;

import java.util.List;

import lombok.Data;

@Data
public class ValidationResult {//-->Response entity maari tha
	private boolean isValid = true;
	private List<String> errors;
	private Object object;

}
