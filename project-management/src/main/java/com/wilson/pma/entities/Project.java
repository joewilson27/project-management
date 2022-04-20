package com.wilson.pma.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity // an entity annotation is a mark that could tell the java that this class is an entity
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) // for auto generate an unique ID
	private long projectId;
	
	private String name;
	
	private String stage; // NOTSTARTED, COMPLETED, INPROGRESS
	
	private String description;
	
	// one (project) to (assign employee) many
	// One project could be assigned to many employees
	// when we run this project, this would be create a mapping table for binding project and employee
	/*@OneToMany
	private List<Employee> employees; 
	*/
	
	// this would be create a field in Employee table, and put a field for catch this mapped in Employee
	// entity
	@OneToMany(mappedBy="theProject") // this means that kita harus define a field on Employees entity dgn nama theProject
	private List<Employee> employees; // when it runs, table employee can include a field for project

	public Project() {
		
	}
	
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
