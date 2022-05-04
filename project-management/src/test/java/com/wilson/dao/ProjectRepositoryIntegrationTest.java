package com.wilson.dao;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test; // JUnit 5
//import org.junit.Test; // JUnit 4
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.wilson.pma.ProjectManagementApplication;
import com.wilson.pma.dao.ProjectRepository;
import com.wilson.pma.entities.Project;

/*
 * executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts= {"classpath:schema.sql", "classpath:data.sql"}
 * akana meng-eksekusi file schema.sql dan data.sql sebelum application running pertama kali. kita tdk perlu menspesifik
 * direktor tempat file schema.sql dan data.sql karena spring otomatis mencari dari root project
 * 
 * 
 * 
 * why the @SqlGroup commented, because of this
 * https://www.udemy.com/course/spring-framework-web-development-2020/learn/lecture/16739956#questions/15535596
 * */

// kita ingin agar ketika integration test mode ini di jalankan, ia akan me-load class utama "ProjectManagementApplication"
@ContextConfiguration(classes=ProjectManagementApplication.class)
@RunWith(SpringRunner.class)
@DataJpaTest // it is used for, if you have a temporary database that you want to write test case
@SqlGroup({@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts={"classpath:schema.sql", "classpath:data.sql"}),
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:drop.sql")})
public class ProjectRepositoryIntegrationTest {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Test
	public void ifNewProjectSaved_thenSuccess() {
		
		Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
		proRepo.save(newProject);
		// setelah menambah data newProject, maka akan ada 5 data project (4 dari data yg di insert pada file data.sql)
		
		// expected 5 data in application when running this apps
		//assertEquals(5, proRepo.findAll().size()); // run this with no @SqlGroup
		assertEquals(9, proRepo.findAll().size()); // run this with @SqlGroup
		
	}
	
}
