package com.ndevaki.employee.management.model.dto;

import java.util.ArrayList;
import java.util.List;

/*
 * Used this object to return list of employee object in list employees api
 */
public class EmployeeListDTO {

	ArrayList<EmployeeDTO> employeeDTOs;
	int limit,offset;
	Long totalCount;
	
	public EmployeeListDTO() {
		
	}
	
	public ArrayList<EmployeeDTO> getEmployeeDTOs() {
		return employeeDTOs;
	}

	public void setEmployeeDTOs(ArrayList<EmployeeDTO> employeeDTOs) {
		this.employeeDTOs = employeeDTOs;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}

	public EmployeeListDTO addEmployeeDTOList(ArrayList<EmployeeDTO> employeeDTOs) {
		this.employeeDTOs=employeeDTOs;
		return this;
	}

    public EmployeeListDTO addTotalCount(Long totalCount) {
    	this.totalCount=totalCount;
    	return this;
    }
	
    public EmployeeListDTO addOffset(int offset) {
    	this.offset=offset;
    	return this;
    }
    
    public EmployeeListDTO addLimit(int limit) {
    	this.limit=limit;
    	return this;
    }
    
    public EmployeeListDTO build() {
    	return this;
    }
}
