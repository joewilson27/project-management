package com.wilson.pma.dto;
// dto = data transfer object
public interface EmployeeProject {
	
	// Need to have the property names begin with 'get'
	// property di bawah ini mengacu pada query select data di EmployeeRepository
	public String getFirstName();
	public String getLastName();
	public int getProjectCount();
	
}
