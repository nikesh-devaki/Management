package com.ndevaki.employee.management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@Configuration
public class SecurityConfiguration extends WebSecurityConfiguration{
	
	//How to validate login request with db credentials?
	
	//How to authorize urls based on role?
	
	//How to create associate roles to urls?
	
//	public void configure(HttpSecurity security) throws Exception {
//		security.authorizeRequests().antMatchers("/api/v1/**").access("hasRole('User')").
//		and().formLogin();
//		
//	}
}
