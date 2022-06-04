package com.wilson.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.wilson.pma.dao.EmployeeRepository;
import com.wilson.pma.entities.Employee;
import com.wilson.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees") // kita ubah endpointnya agar ke trigger endpoint rest, karena by default, @RequestMapping akan menjalankan normal endpoint yg dimana akan menuju halaman employee ketika 
// alamat ini di hit, 
// karena jika  Rest Repositories ini kita enabling, maka yg terjadi
// si Rest Repo ini pada background sudah membentuk endpoint secara otomatis pada dari controller utama kita,
// maka controller utama ini kita ganti agar dia menjalankan rest
public class EmployeeController {
	
	@Autowired // remove this Autowired for test
	EmployeeService empService; //EmployeeRepository empRepo;
	
	// constructor injection (video 33), we dont need an annotation injection
	/*public EmployeeController(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}*/
	
	
	// Setter Injection
	/*@Autowired
	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}*/
	
	// @GetMapping tanpa param, maka method ini akan dipanggil ketika kita melakukan request localhost:8080/employees
	// 'employees' dr request mapping Controller EmployeeController 
	@GetMapping
	public String displayEmployees(Model model) {
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee anEmployee = new Employee();
		
		model.addAttribute("employee", anEmployee); 
		
		return "employees/new-employee"; 
	}
	
	@PostMapping("/save")
	public String createEmployee(Model model, Employee employee) {
		// save to the database using an employee crud repository
		empService.save(employee);
		
		// this is an url redirect, not a template view
		return "redirect:/employees";// best practice, if you save your data, better to redirect to prevent a double submissions (submit berkali-kali)
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		
		Employee theEmp = empService.findByEmployeeId(theId);
		
		model.addAttribute("employee", theEmp);
		
		
		return "employees/new-employee";
	}
	
	@GetMapping("delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		Employee theEmp = empService.findByEmployeeId(theId);
		empService.delete(theEmp);
		return "redirect:/employees";
	}
	
	
}
