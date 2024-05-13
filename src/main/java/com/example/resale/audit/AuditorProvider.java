package com.example.resale.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorProvider implements AuditorAware<String> {

	    @Override
	    public Optional<String> getCurrentAuditor() {
	        // Logic to retrieve the current auditor, e.g., the username of the logged-in user
	        // Return an Optional<String> containing the auditor's username
	        // For simplicity, let's return a static username "admin" as an example
	        return Optional.of("User");
	    }
	}


