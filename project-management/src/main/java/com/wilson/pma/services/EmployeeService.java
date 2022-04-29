package com.wilson.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wilson.pma.dao.EmployeeRepository;

@Service // jika kita me-remove @Service, maka spring tidak akan tahu bahwa class ini minta di inject
public class EmployeeService {
	
	//@Qualifier("staffRepositoryImpl1") // kita jg dapat menggunakan Qualifier di field injection
	//@Autowired // field injection
	//EmployeeRepository empRepo;
	
	// constructor injection
	/*public EmployeeService(EmployeeRepository empRepo) {
		super();
		this.empRepo = empRepo;
	}*/
	
	// setter injection
	//@Qualifier("staffRepositoryImpl1") // bisa juga di setter injection
	/*@Autowired // for setter injection, we need an annotation @Autowired
	public void setEmpRepo(EmployeeRepository empRepo) {
		this.empRepo = empRepo;
	}*/
	
	// what happen when you have multiple beanz trying to compete for the same injection? 
	IStaffRepository empRepo;
	
	public EmployeeService(@Qualifier("staffRepositoryImpl1") IStaffRepository empRepo) { // REMEMBER, lower firstletter for @Qualifier
		super();
		this.empRepo = empRepo;
		/*
		 * Jadi, jika kita contoh debug pada baris sini, dan kita tidak membuat class/interface annotation utk di inject ke spring context, maka waktu component scanning dia akan mengeluarkan error:
		 * Parameter 0 of constructor in com.wilson.pma.services.EmployeeService required a bean of type 'com.wilson.pma.services.IStaffRepository' that could not be found.
		 * 
		 * Action:
		 * Consider defining a bean of type 'com.wilson.pma.services.IStaffRepository' in your configuration.
		 * 
		 * 
		 * Lalu, ketika sebuah repo yg di implement ke multiple class, ketika component scanning, class mana yg akan di jadikan acuan?
		 * 
		 * */
	}
	
}
