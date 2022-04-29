package com.wilson.pma.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

// this annotation gives the spring framework the responsibility of loading this an instance of this class in the spring context
//@Service // when you're dealing with service

// annotation ini sama juga dgn @Service
//@Component // when you're dealing with a particular component


//annotation ini sama juga dgn @Service dan @Component
@Repository // when you're dealing with data
public class DataCleansingService {
	
	public DataCleansingService() {
		super();
	}
	
}
