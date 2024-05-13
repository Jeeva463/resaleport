package com.example.resale.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import com.example.resale.enums.GenderType;


public class ValidationUtil {

	public static boolean isNameValid(String name) {
		return name !=null && !name.isEmpty();
	}
	
	public static String getFormattedString(String value) {
		if (value != null) {
			return value.trim();//trim enbudhu space ulladhai space illamal kaattum
		}
		return value;
		}

	public static boolean isValidName(String name) {
		if (name == null || name.isEmpty()) {
			return false;
		}

		// Define the allowed name pattern
		String namePattern = "^[\\p{L} '-]+$";//\p{L}: Matches any kind of letter from any language.

		// Check if the name matches the pattern
		if (!name.matches(namePattern)) {
			return false;
		}

		// Check for length constraints
		if (name.length() < 1 || name.length() > 50) {
			return false;
		}
		return true;
	}
	
	public static boolean isAgeRequired(String age) {
	    return age == null || age.trim().isEmpty();
	}
	
	public static int calculateAge(String dob) {
	    LocalDate today = LocalDate.now();
	    final String DATE_PATTERN = "dd.MM.yyyy";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
		LocalDate date = LocalDate.parse(dob, formatter);
	    return Period.between(date, today).getYears();
	}
	
   public static boolean isEighteenOrOlder(String birthDate, String reportedAge) {
		
	    int calculatedAge = calculateAge(birthDate);
	    
	    int age = Integer.parseInt(reportedAge);
	    
	    return calculatedAge >= 18 && age > 18;
	}
  
   public static boolean isDateOfBirthAgeMatcher(String dob , String reportedAge) {
	int calculatedAge = calculateAge(dob);
	int age = Integer.parseInt(reportedAge);
	return calculatedAge == age;
}
	
//	
//	public static boolean isnullorEmpty(String value) {
//		return null==value || value.trim().isEmpty();
//	}
//	
//	public static boolean isUserNameValid(String userName) {
//		if (userName == null || userName.length() < 3 || userName.length() > 20) {
//			return false;
//		}
//		return userName.matches("^[a-zA-Z0-9_]+$");
//	}
//	
//	public static boolean isValidEmailId(String value) {
//		String regex = "^(?=.{1,64}@)[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9-.]+$";
//		Pattern p = Pattern.compile(regex);
//		Matcher m = p.matcher(value);
//		return m.matches();
//	}
//	
//
//	public static boolean isValidMobileNumber(String value) {
//		String regex = "(?:\\s+|)((0|(?:(\\+|)91))(?:\\s|-)*(?:(?:\\d(?:\\s|-)*\\d{9})|(?:\\d{2}(?:\\s|-)*\\d{8})|(?:\\d{3}(?:\\s|-)*\\d{7}))|\\d{10})(?:\\s+|)";
//		Pattern p = Pattern.compile(regex);
//		String s = String.valueOf(value);
//		Matcher m = p.matcher(s);
//		return m.matches();
//	}
//	
//	public static boolean isDateOfBirthrequired(String dateOfBirth) {
//		return dateOfBirth == null;
//	}
//	
//
//	public static boolean isDateOfBirthValid(String dob) {
//		final String DATE_PATTERN = "dd.MM.yyyy";
//		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
//		LocalDate date = LocalDate.parse(dob, formatter);
//		
//		if (dob == null || dob.isEmpty()) {
//			return false;
//		}
//		if (date.isAfter(LocalDate.now())) {
//			return false;
//		}
//		return true;
//		}
//	
//	
//	public static boolean isGenderrequired(GenderType gender) {
//		return gender == null;
//	}
//	
//	public static GenderType getFormattedGender(GenderType gender) {
//		switch (gender) {
//		case MALE:
//			return GenderType.MALE;
//		case FEMALE:
//			return GenderType.FEMALE;
//		case OTHER:
//			return GenderType.OTHER;
//		}
//		return null;
//	}
//	
//	public static boolean isGenderValid(GenderType gender) {
//		String genderTypetoString = gender.toString();
//		return genderTypetoString.equalsIgnoreCase("male") || genderTypetoString.equalsIgnoreCase("female")
//				|| genderTypetoString.equalsIgnoreCase("other");
//	}
//
//	public static boolean isPasswordValid(String password, String conformPassword) {
//		// Check if both password and confirmPassword are not null and are equal
//				if (password == null || conformPassword == null || !password.equals(conformPassword)) {
//					return false;
//				}
//
//				// Regex that checks for the minimum requirements
//				String passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
//
//				// Use Pattern.matches to check if the password meets the regex requirements
//				if (!Pattern.matches(passwordRegex, password)) {
//					return false;
//				}
//
//				// Optional: Check for specific special characters if needed
//				// For example, to ensure that the password contains at least one '@' character
//				boolean hasSpecialChar = password.contains("@");
//
//				return hasSpecialChar;
//	}

}
