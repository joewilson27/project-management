package com.wilson.pma.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // by adding this, it would be added to Spring Context (you know what a spring context is)
@EnableWebSecurity // there a kinds of security, but we are dealing with web security in this lecture
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// we are here using in-memory authentication to the project, there many various authentication
		// jika skrg jalankan app dan buka localhost, ketika login menggunakan myuser dan password di bawah
		// tidak bisa, karena spring tidak mau ada authentication yg terbuka pada source
		auth.inMemoryAuthentication()
			.withUser("myuser")
				.password("pass")
				.roles("USER")
				.and() // with this, we can add multiple user, so in this case you can login with myuser and joe
				.withUser("joe")
					.password("pass2")
					.roles("USER");
		
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // ada garis karena deprecreated, ktia gunakan ini hanya utk melihat functionality dari 
		// configure kita di atas, agar kita bisa pake password yg kita defina di atas "password"
		// istilahnya meng-inject
	}
	
	
	
}
