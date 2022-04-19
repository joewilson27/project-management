package com.wilson.pma.controllers;

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
	
	@Autowired
	EmployeeRepository empRepo;
	
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
