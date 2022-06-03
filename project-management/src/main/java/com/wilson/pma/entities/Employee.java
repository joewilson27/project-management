package com.wilson.pma.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wilson.pma.validators.UniqueValue;

@Entity
public class Employee {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_seq")  //@GeneratedValue(strategy=GenerationType.IDENTITY) // akan membuat data id baru meski data yg existing tidak di buat menggunakan entity ini, misalnya dr data .sql
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator")
	@SequenceGenerator(name = "employee_generator", sequenceName = "employee_seq", allocationSize = 1) // got error before this, so I added this @SequenceGenerator
	private long employeeId;
	
	@NotNull
	@Size(min=2, max=50) 
	private String firstName;
	
	@NotNull
	@Size(min=1, max=50) 
	private String lastName;
	
	@NotNull
	@Email
	//@Column(unique = true) // it means that email is unique on table, kalo mau dari column utk validasi not null tinggal tambah param nullable = false
	// we commented out @Column above, because our setting ddl in dev properties 
	// we set ddl to none, so this annotation does nothing.
	@UniqueValue // this is an annotation custom @UniqueValue 
	private String email;
	
	// this for mapped an employee to a project when we define mappedBy in Project
	// Many employees could be assigned to one project
	/*@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinColumn(name="project_id") // this would be a name for field on table Employee, when it runs, table employee can include a field for this project that called "project_id"
	private Project project; // theProject harus sama seperti yg di set pada table yg ingin di join
	// dalam case ini pada entity Project mappedBy="project"
	*/
	
	// Many to many relationship, so one employee can holds many projects, satu employee dapat terasosiasi banyak project
	// pada entity yang mau di mapping (in this case both of employee & project), would be assign this @JoinTable
	// annotation. So it should be required this annotation to both entities 
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
			fetch = FetchType.LAZY)
	@JoinTable(name="project_employee",
	   joinColumns=@JoinColumn(name="employee_id"),
	   inverseJoinColumns=@JoinColumn(name="project_id")
	)
	@JsonIgnore
	private List<Project> projects;
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	// we no longer needs these setters and getters with type project, because now we are using
	// List<Project>
//	public Project getProject() {
//		return project;
//	}
//
//	public void setProject(Project project) {
//		this.project = project;
//	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	public long getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
