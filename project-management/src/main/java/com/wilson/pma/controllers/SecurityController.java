package com.wilson.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.wilson.pma.dao.UserAccountRepository;
import com.wilson.pma.entities.UserAccount;

@Controller
public class SecurityController {
	
	@Autowired
	UserAccountRepository accountRepo; // pada contoh ini, instruktor hanya ingin save time, jadi dia langsung inject dari interface, tanpa membuatnya di Service seperti yg ia lakukan pada project dan employee
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder; // bisa digunakan untuk encode dan decode
	
//	@Autowired
//	UserAccountService userRepo; // my version
	
	@GetMapping("/register")
	public String register(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(Model model, UserAccount user) {
		user.setPassword(bCryptEncoder.encode(user.getPassword())); // we are encode the user password before save to the database
		//userRepo.save(user); // my version
		accountRepo.save(user);
		return "redirect:/";
	}
	
}
