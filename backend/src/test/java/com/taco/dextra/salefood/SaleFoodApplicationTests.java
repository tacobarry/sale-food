package com.taco.dextra.salefood;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class SaleFoodApplicationTests {
	
	@LocalServerPort
	protected int port;
	
	protected String getRootUrl() {
		return "http://localhost:" + this.port + "/api";
	}
	
	@Before
	public void setUp() throws Exception {
		RestAssured.port = this.port;
	}

}
