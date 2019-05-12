package test.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rmpksoft.aop.config.ServiceConfig;
import com.rmpksoft.aop.config.WebMvcConfig;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebMvcConfig.class, ServiceConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPostController {
	
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeAll
	void initAll() {
		System.out.println("Inside initAll.........");
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}
	
	@Test
	@DisplayName("Initializing testInit")
	void testInit() {
		System.out.println("Inside testInit.........");
		assertNotNull(wac);
		assertNotNull(mockMvc);
		
		ServletContext servletContext = wac.getServletContext();
		assertNotNull(servletContext);
		assertTrue(servletContext instanceof MockServletContext);
	}

}
