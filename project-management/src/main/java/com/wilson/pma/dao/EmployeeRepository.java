package com.wilson.pma.dao;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.wilson.pma.dto.EmployeeProject;
import com.wilson.pma.entities.Employee;

//@Repository
//@Profile("prod") // interface ini akan run on profile prod
//public interface EmployeeRepository extends CrudRepository<Employee, Long> { // Long is data type of primary key entity Employee

// this annotation is for using hybrid to the rest api and webpages
@RepositoryRestResource(collectionResourceRel="apiemployees", path="apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {	
//	@Override // using findAll from CrudRepository
//	public List<Employee> findAll();
	
	@Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount "
			+ "FROM employee e LEFT JOIN project_employee pe ON pe.employee_id = e.employee_id "
			+ "GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();
	
	public Employee findByEmail(String value); // spring knows we want to find data by Email
	
	public Employee findByEmployeeId(long theId); // spring knows we want to find data by EmployeeId
	
}
