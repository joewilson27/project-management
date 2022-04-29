package com.wilson.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wilson.pma.dao.EmployeeRepository;
import com.wilson.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired // remove this Autowired for test
	EmployeeRepository empRepo;
	
	// constructor injection (video 33), we dont need an annotation injection
	/*public EmployeeController(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}*/
	
	
	// Setter Injection
	/*@Autowired
	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}*/
	
	// @GetMapping tanpa param, maka method ini akan dipanggil ketika kita melakukan request localhost:8080/employees
	// 'employees' dr request mapping Controller EmployeeController 
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee anEmployee = new Employee();
		
		model.addAttribute("employee", anEmployee); 
		
		return "employees/new-employee"; 
	}
	
	@PostMapping("/save")
	public String createEmployee(Model model, Employee employee) {
		// save to the database using an employee crud repository
		empRepo.save(employee);
		
		// this is an url redirect, not a template view
		return "redirect:/employees/new";// best practice, if you save your data, better to redirect to prevent a double submissions (submit berkali-kali)
	}
	
	
}
