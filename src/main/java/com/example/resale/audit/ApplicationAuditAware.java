package com.example.resale.audit;

import java.util.Optional;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.resale.entity.User;

import io.micrometer.common.lang.NonNull;

public class ApplicationAuditAware {

	@NonNull
    public Optional<String> getCurrentAuditor() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null|| !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken){
            return Optional.of("unauthorized");
        }
        User userPrincipal=(User) authentication.getPrincipal();
        return Optional.ofNullable(userPrincipal.getName());
    }
}



