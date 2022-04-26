package com.wilson.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilson.pma.dao.EmployeeRepository;
import com.wilson.pma.dao.ProjectRepository;
import com.wilson.pma.dto.EmployeeProject;
import com.wilson.pma.dto.ChartData;
import com.wilson.pma.entities.Project;

@Controller
public class HomeController {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		Map<String, Object> map = new HashMap<>();
		
		// we are querying the database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		List<ChartData> projectData = proRepo.getProjectStatus();
		// Lets convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// the return of this jsonString look like this -> ["NOTSTARTED", 1], ["INPROGRESS",2], ["COMPLETED", 1]
		model.addAttribute("projectStatusCnt", jsonString);
		
		// we are querying the database for employees
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects(); //List<Employee> employees = empRepo.findAll();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt); // nanti di view, jangan lupa property nya sama dgn yg di define di interface EmployeeProject
		
		return "main/home";
	}
	
}
