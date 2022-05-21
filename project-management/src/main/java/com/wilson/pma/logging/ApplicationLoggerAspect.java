package com.wilson.pma.logging;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationLoggerAspect {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// package com.wilson.pma.controllers will be affected by this 
	@Pointcut("within(com.wilson.pma.controllers..*)")
			//+ " || within(com.wilson.pma.dao..*)") // non-aktif utk meng-test join point
	public void definePackagePointcut() {
		// empty method just to name the location specified in the pointcut
		// method ini hanya utk meng-include kan package mana yg akan ter-affected
	}
	
	/*
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
	*/
	
	// Join Point -> A Join Point is basically a point during the execution of your program
	// @Before("definePackagePointcut()")
	//@After("definePackagePointcut()")
	//public void logBefore(JoinPoint jp) {
	@Around("definePackagePointcut()") // Advice @Around seperti gabungan dari kedua advice @After @Before
	public Object logAround(ProceedingJoinPoint jp) {
//		log.debug("\n \n \n");
//		log.debug("************* Before Method Execution *************** \n {}...{} () with arguments[s] = {}",
//				jp.getSignature().getDeclaringTypeName(),
//				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
//		log.debug("_________________________________________________ \n \n \n ");
		
		// sample log with advice Before
		//2022-05-22 00:47:27.756[0;39m [32mDEBUG[0;39m [35m13072[0;39m [2m---[0;39m [2m[nio-8080-exec-2][0;39m [36mc.w.pma.logging.ApplicationLoggerAspect [0;39m [2m:[0;39m ************* Before Method Execution *************** 
		// com.wilson.pma.controllers.HomeController...displayHome () with arguments[s] = [{}]
		//[2m2022-05-22 00:47:27.757[0;39m [32mDEBUG[0;39m [35m13072[0;39m [2m---[0;39m [2m[nio-8080-exec-2][0;39m [36mc.w.pma.logging.ApplicationLoggerAspect [0;39m [2m:[0;39m _________________________________________________ 
		 
		// sample log with advice After
		//[2m2022-05-22 00:50:24.617[0;39m [32mDEBUG[0;39m [35m13072[0;39m [2m---[0;39m [2m[nio-8080-exec-1][0;39m [36mc.w.pma.logging.ApplicationLoggerAspect [0;39m [2m:[0;39m ************* Before Method Execution *************** 
		// com.wilson.pma.controllers.HomeController...displayHome () with arguments[s] = [{versionNumber=dev, projectsList=[], projectStatusCnt=[], employeesListProjectsCnt=[]}]
		//[2m2022-05-22 00:50:24.617[0;39m [32mDEBUG[0;39m [35m13072[0;39m [2m---[0;39m [2m[nio-8080-exec-1][0;39m [36mc.w.pma.logging.ApplicationLoggerAspect [0;39m [2m:[0;39m _________________________________________________ 
		 
		
		log.debug("\n \n \n");
		log.debug("************* Before Method Execution *************** \n {}...{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("_________________________________________________ \n \n \n ");
		
		log.debug("\n \n \n");
		log.debug("************* Before Method Execution *************** \n {}...{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("_________________________________________________ \n \n \n ");
		
		Object o = null;
		
		try {
			o = jp.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log.debug("************* After Method Execution *************** \n {}...{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("_________________________________________________ \n \n \n ");
		
		log.debug("\n \n \n");
		log.debug("************* Before Method Execution *************** \n {}...{} () with arguments[s] = {}",
				jp.getSignature().getDeclaringTypeName(),
				jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
		log.debug("_________________________________________________ \n \n \n ");
		
		return o;
	}
	
}
