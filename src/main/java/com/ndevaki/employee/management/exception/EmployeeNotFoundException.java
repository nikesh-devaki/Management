package com.ndevaki.employee.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException {

	/*
	 * Throws this error if employee with id is not found
	 */
	public EmployeeNotFoundException(String string) {
		super();
	}

}
