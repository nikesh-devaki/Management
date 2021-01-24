package com.ndevaki.employee.management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {

	@ExceptionHandler(Throwable.class)
	public ModelAndView handleException(Throwable error) {
		ModelAndView mv=new ModelAndView();
		mv.addObject("message", error.getMessage());
		mv.setViewName("error");
		return mv;
	}
}
