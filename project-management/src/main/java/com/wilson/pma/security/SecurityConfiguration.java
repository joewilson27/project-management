package com.wilson.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // by adding this, it would be added to Spring Context (you know what a spring context is)
@EnableWebSecurity // there a kinds of security, but we are dealing with web security in this lecture
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder; // ingatkan, kita menginject class WebConfig (dimana dia sudah menggunakan annotatino @@Configuration dan ada @Bean dgn type BCryptPasswordEncoder)
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		// using in-memory
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
					.roles("USER")
				.and()
				.withUser("manageUser")
					.password("pass3")
					.roles("ADMIN");
		*/
		
		/*
		// using jdbc with Default Schema
		auth.jdbcAuthentication().dataSource(dataSource)
			.withDefaultSchema() // LOOK AT THIS
			.withUser("myuser")
				.password("pass")
				.roles("USER")
				.and() 
				.withUser("joe")
					.password("pass2")
					.roles("USER")
				.and()
				.withUser("manageUser")
					.password("pass3")
					.roles("ADMIN");
		*/
		
		/*
		// Using JDBC with customize schema using query
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, enabled "
					+ "from users where username = ?" ) // look up for the username
			.authoritiesByUsernameQuery("select username, authority "
					+ "from authorities where username = ?"); // look up for the authority
		
		// so with this configure using jdbc, when you run the apps, then you can see
		// the table USERS has created with these following users above (myuser, joe, manageUser)
		// on jdbc h2 http://localhost:8080/h2-console
		// also you can see an AUTHORITIES table that contains the authority of the users
		// the documentation >> https://docs.spring.io/spring-security/site/docs/3.0.x/reference/appendix-schema.html
		*/
	
		// using a real database
		auth.jdbcAuthentication()
			.usersByUsernameQuery("select username, password, enabled "
					+ "from user_accounts where username = ?" ) // look up for the username
			.authoritiesByUsernameQuery("select username, role "
					+ "from user_accounts where username = ?")
			.dataSource(dataSource)
			.passwordEncoder(bCryptEncoder);
		
	}
	
	/*
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance(); // ada garis karena deprecreated, ktia gunakan ini hanya utk melihat functionality dari 
		// configure kita di atas, agar kita bisa pake password yg kita defina di atas "password"
		// istilahnya meng-inject
	}
	*/
	
	// this method will give the authorization to take activity over our websites,
	// only an authorized user can do the task
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests()
			.antMatchers("/projects/new").hasRole("ADMIN") // only user that has role ROLE_ADMIN could create project
			.antMatchers("/projects/save").hasRole("ADMIN")
			.antMatchers("/employees/new").hasRole("ADMIN") //hasAuthority("ADMIN") // user yg bisa mengakses, adl user yg memiliki value ADMIN pada kolom role pd table user_accounts
			.antMatchers("/employees/save").hasRole("ADMIN") //hasAuthority("ADMIN") // user yg bisa mengakses, adl user yg memiliki value ADMIN pada kolom role pd table user_accounts
			// using this method if using jdbc
			//.antMatchers("/h2_console/**").permitAll()
//			.antMatchers("/").authenticated().and().formLogin(); // all users can access to the endpoint "/" if they're authenticated
			.antMatchers("/", "/**").permitAll() // urutan ini berpengaruh pada akses aplikasi, jika kita menaruh baris ini paling atas, karena di baris ini kita menggunakan "/**" yakni semua url/akses, maka rule berikutnya jadi tidak berguna
			.and()
			.formLogin();
			// ketika user yg tidak mempunyai ROLE ADMIN mencoba akses ke endpoint "/project/new"
		// maka aplikasi akan menolak
		
		// disable this if using jdbc
//		http.csrf().disable();
//		http.headers().frameOptions().disable();
		
		
	}
	
}
