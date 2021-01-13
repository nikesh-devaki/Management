package com.ndevaki.employee.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ndevaki.employee.management.dao.EmployeeRepository;
import com.ndevaki.employee.management.model.Employee;
import com.ndevaki.employee.management.model.Employee.Status;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public Employee deActivate(Long id) {
		Employee employee=EmployeeRepository.findById(id);
		employee.setStatus(Status.INACTIVE);
		return employeeRepository.save(employee);
	}
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
}
