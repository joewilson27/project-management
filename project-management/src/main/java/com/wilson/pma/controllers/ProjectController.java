package com.wilson.pma.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wilson.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	//@RequestMapping("/new")
	@GetMapping("/new") // other way for mapping method GET controller
	public String displayProjectForm(Model model) {
		
		Project aProject = new Project(); // objek empty ini akan di isi di form later dlm form new-project.html, so dr form akan mengirimkan sebuah objek Project yg sudah ter-fill
		
		model.addAttribute("project", aProject); // -> the first parameter "project" is the name that would be caught in html for form th:object="${project}"
		
		return "new-project"; // -> thymeleaf would find a page new-project.html in src/main/resources/templates
	}
	
	//@RequestMapping(value="/save", method=RequestMethod.POST)
	@PostMapping("/save") // other way for mapping method POST controller
	public String createProject(Project project, Model model) {
		// this should handle saving to the database...
	}

}
