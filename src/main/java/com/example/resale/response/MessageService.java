package com.example.resale.response;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final MessageSource messageSource;
	
	public String messageResponse(String Key) {
		return messageSource.getMessage(Key, null, Locale.ENGLISH);
	}

}
