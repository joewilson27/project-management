package com.wilson.pma.logging;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// package com.wilson.pma.controllers will be affected by this 
	@Pointcut("within(com.wilson.pma.controllers..*)"
			+ " || within(com.wilson.pma.dao..*)")
	public void definePackagePointcut() {
		// empty method just to name the location specified in the pointcut
	}
	
	//@Before("definePackagePointcut()")
	@After("definePackagePointcut()") // kita define method yg meng-annotation @Pointcut. Method ini akan berjalan  di setiap baris package wilson.pma.controllers package juga wilson.pma.dao 
	public void log() {
		// debug ini akan berjalan di setiap baris code yg ada pada pakcage com.wilson.pma.controllers, com.wilson.pma.controllers dan setiap package yg di include kan menggunakan within()
		// AFTER method nya di eksekusi, artinya log di bawah akan berjalan setelah method
		// tersebut selesai di eksekusi. Sedangkan @Before() akan berjalan sebelum method di eksekusi
		log.debug(" ----------------------------------------------------- "); 
		// ketika menjalankan salah satu method pada browser while the app running,
		// misal buka display employee, maka log di atas akan muncul pada console
		// @After() dan @Before() ini bisa juga disebut advice dalam AOP (Aspect Oriented Programming) land 
	}
	
}
