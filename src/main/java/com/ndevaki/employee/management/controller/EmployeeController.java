package com.ndevaki.employee.management.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ndevaki.employee.management.model.Employee;
import com.ndevaki.employee.management.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	@Value("0")
	private String OFFSET;
	@Value("15")
	private String LIMIT;
	
	@Autowired
	EmployeeService employeeService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity create(Employee employee) {
		employeeService.save(employee);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public Employee get(@PathVariable("id") Long id) {
		
		return Employee.findById(id);
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public Employee get(@RequestParam(value = "offset", defaultValue =OFFSET) int offset,
						@RequestParam(value = "limit", defaultValue =LIMIT) int limit) {
		
		return Employee.findById(id);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		boolean status=employeeService.deActivate(id);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public void update(Employee employee) {
		employee.save();
	}
	
}
