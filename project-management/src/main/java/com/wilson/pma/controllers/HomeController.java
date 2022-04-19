package com.wilson.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.wilson.pma.dao.EmployeeRepository;
import com.wilson.pma.dao.ProjectRepository;
import com.wilson.pma.entities.Employee;
import com.wilson.pma.entities.Project;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) {
		
		// we are querying the database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		// we are querying the database for employees
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employeesList", employees);
		
		return "main/home";
	}
	
}
