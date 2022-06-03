package com.wilson.pma.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wilson.pma.dao.EmployeeRepository;
import com.wilson.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	
	@Autowired
	EmployeeRepository empRepo; // better practice is using a service class that included this employee repository
	
	@GetMapping
	public Iterable<Employee> getEmployees(){
		return empRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return empRepo.findById(id).get();
	}
	
	// @Valid is for validating a request data from user
	@PostMapping(consumes = "application/json") // type request yg di get dari executor
	@ResponseStatus(HttpStatus.CREATED) // response to be get by the executor
	public Employee create(@RequestBody @Valid Employee employee) {
		return empRepo.save(employee);
	}
	
	//
	// Put for updating data
	// will delete project_employee
	//
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody @Valid Employee employee) {
		return empRepo.save(employee); 
	}
	
	//
	//  Update by patch, only certain field that updated
	// will not delete data from project_employee, because its updated only for firstName, lastName and email only
	//
	@PatchMapping(path="/{id}", consumes="application/json")
	public Employee partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Employee patchEmployee) {
		Employee emp = empRepo.findById(id).get();
		
		if (patchEmployee.getEmail() != null) {
			emp.setEmail(patchEmployee.getEmail());
		}
		if (patchEmployee.getFirstName() != null) {
			emp.setFirstName(patchEmployee.getFirstName());
		}
		if (patchEmployee.getLastName() != null) {
			emp.setLastName(patchEmployee.getLastName());
		}
		
		return empRepo.save(emp);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		// use try cactch to handle deleting data that id not existing
		try {
			empRepo.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			// so dengan try catch ini, jika kita mencoba menjalankan delete data
			// dimana kita ingin mendelete data yg id nya sudah tidak ada di table,
			// maka tidak akan error / method doesnt exist
		}
	}
	
	@GetMapping(params = {"page", "size"})
	@ResponseStatus(HttpStatus.OK)
	public Iterable<Employee> findPaginatedEmployees(@RequestParam("page") int page, 
													 @RequestParam("size") int size){
		Pageable pageAndSize = PageRequest.of(page, size);
		
		return empRepo.findAll(pageAndSize); // findAll method menerima type Pagiable
	}
	
	
}
