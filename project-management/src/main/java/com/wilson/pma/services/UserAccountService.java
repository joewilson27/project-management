package com.wilson.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilson.pma.dao.UserAccountRepository;
import com.wilson.pma.entities.UserAccount;

@Service
public class UserAccountService {
	
	@Autowired
	UserAccountRepository userRepo;
	
	public UserAccount save(UserAccount user) {
		return userRepo.save(user);
	}
	
}
