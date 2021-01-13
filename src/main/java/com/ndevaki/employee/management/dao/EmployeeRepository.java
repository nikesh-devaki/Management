package com.ndevaki.employee.management.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.ndevaki.employee.management.model.Employee;

@Component
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

}
