package com.wilson.pma.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.wilson.pma.entities.UserAccount;

public interface UserAccountRepository extends PagingAndSortingRepository<UserAccount, Long> {
	
}
