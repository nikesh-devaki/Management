package com.ndevaki.employee.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.internal.bytebuddy.build.Plugin.Engine.Source;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ndevaki.employee.management.dao.EmployeeRepository;
import com.ndevaki.employee.management.exception.EmployeeNotFoundException;
import com.ndevaki.employee.management.model.Employee;
import com.ndevaki.employee.management.model.Employee.Status;
import com.ndevaki.employee.management.model.dto.EmployeeDTO;
import com.ndevaki.employee.management.model.dto.EmployeeListDTO;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	
	/*
	 * This method perform employee soft delete
	 */
	public void deActivate(Long id) {
		Employee employee=findEmployeeById(id);
		employee.setStatus(Status.INACTIVE);
		logger.info("Employe is going to be deactivated",employee);
		employeeRepository.save(employee);
	}
	
	/*
	 * This method creates new employee record
	 */
	public EmployeeDTO save(EmployeeDTO employeeDTO) {
		Employee employee=convertEmployeeToEmployeeDTO(employeeDTO);
		employee.setStatus(Employee.Status.ACTIVE);
		Employee savedEmployee=employeeRepository.save(employee);
		logger.info("Employee is saved"+employee.getId());
		return convertEmployeeToEmployeeDTO(savedEmployee);
	}
	
	/*
	 * This method returns list of employees based on offset and limit
	 */
	public EmployeeListDTO searchAll(int offSet,int limit){
		List<Employee> employees =employeeRepository.findAllByStatus(Employee.Status.ACTIVE,PageRequest.of(offSet,limit));
		ArrayList<EmployeeDTO> employeeDTOs=new ArrayList<EmployeeDTO>();
		employeeDTOs=employees.stream()
				.map(employee -> convertEmployeeToEmployeeDTO(employee))
				.collect(Collectors.toCollection(ArrayList::new));
		Long totalEmployeesCount=employeeRepository.countByStatus(Employee.Status.ACTIVE);
		EmployeeListDTO employeeDTOList=new EmployeeListDTO();
		return employeeDTOList.addEmployeeDTOList(employeeDTOs)
						.addLimit(limit)
						.addOffset(offSet)
						.addTotalCount(totalEmployeesCount).build();
	}
	
	/*
	 *This method searches employee by id and returns employeeDTO object 
	 */
	public EmployeeDTO search(Long id) {
		return convertEmployeeToEmployeeDTO(findEmployeeById(id));
	}
	
	/*
	 * This method updates the employee details
	 */
	public void update(EmployeeDTO updateEmployee,Long id) {
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
		logger.info("Updated employee details",actualEmployee);
	}
	
	/*
	 * Queries employee object by employee id
	 */
	private Employee findEmployeeById(Long id) {
		Employee employee=employeeRepository.findByIdAndStatus(id,Employee.Status.ACTIVE);
		if(employee==null) {
			logger.info("Active employee with id is not found",id);
			throw new EmployeeNotFoundException("Id-"+id);
		}
		return employee;
	}
	
	/*
	 * Converts Employee object to EmployeeDTO object
	 * This is used to provide abstraction to employee model object
	 */
	final private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
		 EmployeeDTO employeeDTO = modelMapper.map(employee, EmployeeDTO.class);	
		 employeeDTO.setId(employee.getId());
		 logger.info("returning "+employeeDTO);
		 return employeeDTO;
	}
	
	/*
	 * Converts EmployeeDTO object to Employee.
	 * This is used to provide abstraction to employee model object
	 */
	final private Employee convertEmployeeToEmployeeDTO(EmployeeDTO employeeDTO) {
		 Employee employee = modelMapper.map(employeeDTO, Employee.class);	
		 return employee;
	}
}
