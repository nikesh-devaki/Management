package com.ndevaki.employee.management.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ndevaki.employee.management.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	Employee findByIdAndStatus(Long id,Employee.Status status);
	List<Employee> findAllByStatus(Employee.Status status,Pageable pageable);
	Long countByStatus(Employee.Status status);
}
