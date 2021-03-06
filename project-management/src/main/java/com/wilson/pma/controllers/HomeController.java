package com.wilson.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilson.pma.dto.EmployeeProject;
import com.wilson.pma.dto.ChartData;
import com.wilson.pma.entities.Project;
import com.wilson.pma.services.EmployeeService;
import com.wilson.pma.services.ProjectService;
import com.wilson.pma.springExample.Car;

@Controller
public class HomeController {
	
	@Value("${version}") // value ini kita get dr key yg ada di application.properties, dan kita akan menyimpannya dlm sebuah variable di bawah
	private String ver;
	
	
	/*
	 * kita mencoba meng-inject objek Car dengan Autowired
	 * */
	@Autowired
	Car car; 
	// didnt work --> Field car in com.wilson.pma.controllers.HomeController required a bean of type 'com.joe.pma.springExample.Car' that could not be found.
	// --> Consider defining a bean of type 'com.joe.pma.springExample.Car' in your configuration.
	// jadi coba kita menggunakan annotation @Bean pada ProjectManagementApplication utk object Car ini
	// agar inject @Autowired berjalan pada object Car yg kita buat secara manual dengan @Bean di ProjectManagementApplication
	// So, spring framework is responsible for injecting our dependencies that is what is called dependency
	
	@Autowired
	ProjectService proService; // ProjectRepository proRepo;
	
	@Autowired
	EmployeeService empService; //EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver); // kita akan menampilkan version yg kita define di application.properties ke halaman home 
		
		Map<String, Object> map = new HashMap<>();
		
		// we are querying the database for projects
		List<Project> projects = proService.getAll();
		model.addAttribute("projectsList", projects);
		
		List<ChartData> projectData = proService.getProjectStatus();
		// Lets convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// the return of this jsonString look like this -> ["NOTSTARTED", 1], ["INPROGRESS",2], ["COMPLETED", 1]
		model.addAttribute("projectStatusCnt", jsonString);
		
		// we are querying the database for employees
		List<EmployeeProject> employeesProjectCnt = empService.employeeProjects(); //List<Employee> employees = empRepo.findAll();
		model.addAttribute("employeesListProjectsCnt", employeesProjectCnt); // nanti di view, jangan lupa property nya sama dgn yg di define di interface EmployeeProject
		
		return "main/home";
	}
	
}
