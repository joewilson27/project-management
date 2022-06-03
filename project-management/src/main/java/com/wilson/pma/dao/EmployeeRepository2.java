package com.wilson.pma.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.wilson.pma.dto.EmployeeProject;
import com.wilson.pma.entities.Employee;

//@Repository
//@Profile("test2") // class ini berjalan pada profile dev
public class EmployeeRepository2 implements EmployeeRepository {
	
	// pada EmployeeService, dia menggunakan interface EmployeeRepository pada property empRepo,
	// jika project ini berjalan, maka empRepo tersebut akan menggunakan EmployeeRepository2
	// karena EmployeeRepository2 meng-implement EmployeeRepository (ingat konsep polymorphism)
	
	// findAll ini override method dari interface EmployeeRepository
	@Override
	public List<Employee> findAll() { 
		// hard-coded add data Employee to page Employee
		Employee emp1 = new Employee("Mike", "Malone", "maloney@gmail.com");
		Employee emp2 = new Employee("Tom", "Bradley", "brad@gmail.com");
		Employee emp3 = new Employee("Jonathan", "Hemzworth", "hemzy@gmail.com");
		
		return Arrays.asList(emp1, emp2, emp3);
	}
	
	@Override
	public <S extends Employee> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Employee> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Employee> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Employee> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Employee entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Employee> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EmployeeProject> employeeProjects() {
		// hard coded values for data in home page
		EmployeeProject empProj = new EmployeeProject() {

			@Override
			public String getFirstName() {
				// TODO Auto-generated method stub
				return "Mike";
			}

			@Override
			public String getLastName() {
				// TODO Auto-generated method stub
				return "Maloney";
			}

			@Override
			public int getProjectCount() {
				// TODO Auto-generated method stub
				return 10;
			}
			
		};
		
		return Arrays.asList(empProj);
	}

	@Override
	public Employee findByEmail(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findByEmployeeId(long theId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Employee> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Employee> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
