package com.wilson.pma.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;

@Entity // an entity annotation is a mark that could tell the java that this class is an entity
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // for auto generate an unique ID
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
	/*@OneToMany(mappedBy="project") // this means that kita harus define a field on Employees entity dgn nama 'project'
	private List<Employee> employees; // when it runs, table employee can include a field for project
	*/
	
	// this would be many to many, and would automatically create a mapping table for both 
	// project entity and employee and it would have an ability to create many to many relationship between these enitities
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY) // we also use the cascade because nanti kalau ada edit data pada data project, maka akan berpengaruh ke data employee yg terasosiasi dgn data ini
	@JoinTable(name="project_employee",
			   joinColumns=@JoinColumn(name="project_id"),
			   inverseJoinColumns=@JoinColumn(name="employee_id")
	)
	private List<Employee> employees; // many projects could be associated to one employee, satu employee dapat terhubung ke banyak project
	// nama table dr table mapping yg akan tercreate nanti adl table 'project_employee'
	// dan foreign key dr table project ke table project_employee adl 'project_id'
	// dan foreign key yg akan terasosiasi dgn table project ini pada table mapping adl employee_id
	
	public Project() {
		
	}
	
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
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

	// convenience method:
	public void addEmployee(Employee emp) {
		if (employees == null) {
			employees = new ArrayList<>();
		}
		employees.add(emp);
	}	
	
}
