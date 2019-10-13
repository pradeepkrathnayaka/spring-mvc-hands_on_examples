package com.rmpksoft.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
public class HomeControllerMockTests {

	@InjectMocks
	private HomeController controller;
	
	private MockMvc mockMvc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(HomeController.class);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertNotNull(controller);
		
		/*Mockito.verify(userManager, Mockito.times(1)).save(Mockito.anyString());
	      Mockito.verify(userManager, Mockito.times(1)).save(Mockito.isA(String.class));
	      Mockito.verify(userManager, Mockito.times(1)).save(Mockito.startsWith("Ca"));
	      Mockito.verify(userManager, Mockito.times(1)).save(Mockito.endsWith("ry"));*/
	}

}
