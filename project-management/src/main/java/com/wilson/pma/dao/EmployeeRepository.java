package com.wilson.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.wilson.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> { // Long is data type of primary key entity Employee

}
