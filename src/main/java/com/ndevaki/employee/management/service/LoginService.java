package com.ndevaki.employee.management.service;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginService {

	public String getLoggedinUserName() {
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication auth=context.getAuthentication();
		Object principal=(Principal) auth.getPrincipal();
		if(principal instanceof UserDetails) {
			return ((UserDetails)principal).getUsername();
		}
		return null;
	}
}
