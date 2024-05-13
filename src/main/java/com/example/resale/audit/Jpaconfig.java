package com.example.resale.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class Jpaconfig {
	@Bean
	public AuditorAware<String> AuditorProvider (){
		return new  ApplicationAuditAware();
	}

}
