package com.wilson.pma.springExample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Di sarankan membuat configuration Bean terpisah dr class lain
 * */
@Configuration
public class ManufactureConfig {
	
	@Bean
	public Car newCar() { // nama methodnya bisa apa aja, yg penting harus di return dgn tipe objek yg mau di inject
		Engine e = new Engine();
		Doors d = new Doors();
		Tires t = new Tires();
		
		return new Car(e, d, t);
	}
	
}
