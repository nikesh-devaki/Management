package com.ndevaki.employee.management.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ndevaki.employee.management.controller.EmployeeController;
import com.ndevaki.employee.management.dao.EmployeeRepository;
import com.ndevaki.employee.management.exception.EmployeeNotFoundException;
import com.ndevaki.employee.management.model.Employee;
import com.ndevaki.employee.management.model.Employee.Status;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	//This method does employee soft delete
	public Employee deActivate(Long id) {
		Employee employee=findEmployeeById(id);
		employee.setStatus(Status.INACTIVE);
		logger.info("Employe id="+id+" is going to be deactivated");
		return employeeRepository.save(employee);
	}
	
	//This method creates new employee record
	public Employee save(Employee employee) {
		employee.setStatus(Employee.Status.ACTIVE);
		Employee savedEmployee=employeeRepository.save(employee);
		logger.info("Employee "+savedEmployee+" is saved");
		return savedEmployee;
	}
	
	//This method returns list of employees based on offset and limit
	public Map<String,Object> searchAll(int offSet,int limit){
		Map<String,Object> responseMap=new HashMap<String,Object>();
		List<Employee> employees =employeeRepository.findAllByStatus(Employee.Status.ACTIVE,PageRequest.of(offSet,limit));
		Long totalEmployees=employeeRepository.countByStatus(Employee.Status.ACTIVE);
		responseMap.put("employees",employees);
		responseMap.put("offset",offSet);
		responseMap.put("limit",limit);
		responseMap.put("total",totalEmployees);
		return responseMap;
	}
	
	//This method searches employee by id and returns employee object
	public Employee search(Long id) {
		return findEmployeeById(id);
	}
	
	//This method updates the employee details
	public void update(Employee updateEmployee,Long id) {
		Employee actualEmployee=findEmployeeById(id);
		if(updateEmployee.getFirstName()!=null) {
			actualEmployee.setFirstName(updateEmployee.getFirstName());
		}
		if(updateEmployee.getLastName()!=null) {
			actualEmployee.setLastName(updateEmployee.getLastName());
		}
		if(updateEmployee.getEmailId()!=null) {
			actualEmployee.setEmailId(updateEmployee.getEmailId());
		}
		if(updateEmployee.getGender()!=null) {
			actualEmployee.setGender(updateEmployee.getGender());
		}
		if(updateEmployee.getAddress()!=null) {
			actualEmployee.setAddress(updateEmployee.getAddress());
		}
		if(updateEmployee.getAge()!=0) {
			actualEmployee.setAge(updateEmployee.getAge());
		}
		employeeRepository.save(actualEmployee);
	}
	
	private Employee findEmployeeById(Long id) {
		Employee employee=employeeRepository.findByIdAndStatus(id,Employee.Status.ACTIVE);
		if(employee==null) {
			throw new EmployeeNotFoundException("Id-"+id);
		}
		return employee;
	}
}
