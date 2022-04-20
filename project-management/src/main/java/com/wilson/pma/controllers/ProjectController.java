package com.wilson.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wilson.pma.dao.EmployeeRepository;
import com.wilson.pma.dao.ProjectRepository;
import com.wilson.pma.entities.Employee;
import com.wilson.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired // for auto create an instance interfaces this ProjectRepository
	ProjectRepository proRep;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proRep.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	//@RequestMapping("/new")
	@GetMapping("/new") // other way for mapping method GET controller
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project(); // objek empty ini akan di isi di form later dlm form new-project.html, so dr form akan mengirimkan sebuah objek Project yg sudah ter-fill
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("project", aProject); // -> the first parameter "project" is the name that would be caught in html for form th:object="${project}"
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project"; // -> thymeleaf would find a page new-project.html in src/main/resources/templates
	}
	
	//@RequestMapping(value="/save", method=RequestMethod.POST)
	@PostMapping("/save") // other way for mapping method POST controller
	public String createProject(Project project, @RequestParam List<Long> employees, Model model) { // employees pada param harus sama dgn th:field pada form create project
		// this should handle saving to the database...
		proRep.save(project);
		
		// kita cari data employee by employees (employeeId) yg di kirim dr form project
		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
		
		// looping employee that selected in chosenEmployees
		for(Employee emp : chosenEmployees) {
			// this part is associated between employee to assigned projects
			emp.setProject(project); // setProject() adl setter di entity Employee, dan kita memasukkan data project yg di kirim dari form ke 
			// property projects di entity employee, nantinya pada table akan di save ke field project_id
			empRepo.save(emp); // save() ini kayak yg di pakai pada save Employee di EmployeeController
		}
		
		// use a redirect to prevent duplicate submissions / utk menghindari double, triple and so on submit pada form 
		return "redirect:/projects/new";
	}

}
