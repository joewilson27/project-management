package com.wilson.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wilson.pma.dao.ProjectRepository;
import com.wilson.pma.entities.Employee;
import com.wilson.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired // for auto create an instance interfaces this ProjectRepository
	ProjectRepository proRep;
	
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
		
		model.addAttribute("project", aProject); // -> the first parameter "project" is the name that would be caught in html for form th:object="${project}"
		
		return "projects/new-project"; // -> thymeleaf would find a page new-project.html in src/main/resources/templates
	}
	
	//@RequestMapping(value="/save", method=RequestMethod.POST)
	@PostMapping("/save") // other way for mapping method POST controller
	public String createProject(Project project, Model model) {
		// this should handle saving to the database...
		proRep.save(project);
		
		// use a redirect to prevent duplicate submissions / utk menghindari double, triple and so on submit pada form 
		return "redirect:/projects/new";
	}

}
