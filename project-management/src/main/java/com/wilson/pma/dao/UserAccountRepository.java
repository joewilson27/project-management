package com.wilson.pma.dao;

import org.springframework.data.repository.CrudRepository;

import com.wilson.pma.entities.UserAccount;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
	
}
