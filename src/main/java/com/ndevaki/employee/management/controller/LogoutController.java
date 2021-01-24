package com.ndevaki.employee.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LogoutController {

	@GetMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		SecurityContext context=SecurityContextHolder.getContext();
		Authentication auth=context.getAuthentication();
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(request,response,auth);
		}
		return "redirect:/"; 
	}
}
