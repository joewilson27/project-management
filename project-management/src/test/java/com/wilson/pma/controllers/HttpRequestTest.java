package com.wilson.pma.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate; // TestRestTemplate --> you can use it to get and retrieve resources like web pages and stuff
	
	
	@Test
	public void homePageReturnsVersionNumberCorrectly_thenSuccess() {
		String renderedHtml = this.restTemplate.getForObject("http://localhost:" + port + "/", String.class);
		assertEquals(renderedHtml.contains("3.3.3"), true); // akan mengecek version di application.properties test
		// kita set 3.3.3 disana, jika disini kita bikin 3.3.1, maka akan error
	}
}
