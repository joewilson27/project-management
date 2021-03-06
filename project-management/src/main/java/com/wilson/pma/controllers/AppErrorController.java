package com.wilson.pma.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController {
	
	@RequestMapping("/error") // @GettMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			
			if(statusCode == HttpStatus.NOT_FOUND.value()) {
				System.out.println("404");
	            return "errorpages/error-404";
	        }
	        else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	        	System.out.println("500");
	            return "errorpages/error-500";
	        }
	        else if(statusCode == HttpStatus.FORBIDDEN.value()) {
	        	System.out.println("403");
	            return "errorpages/error-403";
	        }
		}
		
		return "errorpages/error";
	}

	/* ini udah decrepitated dari interface ErrorController
	public String getErrorPath() {
		return "";
	}
	*/
}
