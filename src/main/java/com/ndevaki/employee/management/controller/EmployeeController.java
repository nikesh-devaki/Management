package com.ndevaki.employee.management.controller;

import java.net.URI;

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

import com.ndevaki.employee.management.model.dto.EmployeeDTO;
import com.ndevaki.employee.management.model.dto.EmployeeListDTO;
import com.ndevaki.employee.management.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

	
	private static final String OFFSET="0";
	
	private static final String LIMIT="15";
	
	@Autowired
	EmployeeService employeeService;
	
	static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	 
	/*
	 * This api to create new employee record
	 */
	@PostMapping
	public ResponseEntity create(@RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO savedEmployee=employeeService.save(employeeDTO);
		logger.info("Created employee resource "+savedEmployee.getId());
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedEmployee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	/*
	 * This api to search employee record by employee id
	 * @param - Employee id
	 */
	@GetMapping("{id}")
	public EmployeeDTO search(@PathVariable("id")final Long id) {
		return employeeService.search(id);
	}
	
	/*
	 * This api to get list of active employees by offset and limit
	 * @param offset default - 0
	 * @param limit default - 15
	 */
	@GetMapping
	public EmployeeListDTO searchAll(@RequestParam(value = "offset", defaultValue =OFFSET) final int offSet,
						@RequestParam(value = "limit", defaultValue =LIMIT) final int limit) {
		return employeeService.searchAll(offSet, limit);
	}
	
	/*
	 * This api to delete employee with input id
	 * @param employee id
	 */
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") final Long id) {
		employeeService.deActivate(id);
		return ResponseEntity.ok().build();
	}
	
	/*
	 * This api to update details of active employee
	 * @param id
	 * @param EmployeeDTO 
	 */
	@PatchMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO) {
		employeeService.update(employeeDTO,id);
		return ResponseEntity.ok().build();
	}
}
