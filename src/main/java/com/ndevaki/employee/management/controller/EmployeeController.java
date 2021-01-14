package com.ndevaki.employee.management.controller;

import java.net.URI;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ndevaki.employee.management.model.Employee;
import com.ndevaki.employee.management.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	
	private static final String OFFSET="0";
	
	private static final String LIMIT="15";
	
	@Autowired
	EmployeeService employeeService;
	
	static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	 
	@PostMapping
	public ResponseEntity create(@RequestBody Employee employee) {
		employeeService.save(employee);
		logger.info("Created employee resource "+employee);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("{id}")
	public Employee search(@PathVariable("id")final Long id) {
		return employeeService.search(id);
	}
	
	@GetMapping
	public Map<String, Object> searchAll(@RequestParam(value = "offset", defaultValue =OFFSET) final int offSet,
						@RequestParam(value = "limit", defaultValue =LIMIT) final int limit) {
		return employeeService.searchAll(offSet, limit);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") final Long id) {
		employeeService.deActivate(id);
	}
	
	@PatchMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Employee employee) {
		employeeService.update(employee,id);
		return ResponseEntity.noContent().build();
	}
}
