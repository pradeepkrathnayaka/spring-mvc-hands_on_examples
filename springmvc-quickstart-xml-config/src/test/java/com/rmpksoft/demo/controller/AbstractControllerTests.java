package com.rmpksoft.demo.controller;

import java.text.MessageFormat;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

public abstract class AbstractControllerTests {

	private static Logger log = LoggerFactory.getLogger(AbstractControllerTests.class);

	@Autowired
	protected WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeAll
	public void setUpBeforeClass() throws Exception {
		System.setProperty("log4j.configurationFile", "log4j2-test.xml");

		mockMvc = MockMvcBuilders.standaloneSetup(getTestedController())
				.setHandlerExceptionResolvers(exceptionResolver()).setViewResolvers(viewResolver()).build();
	}

	protected abstract Object getTestedController();

	protected MockMvc getMockMvc() {
		return mockMvc;
	}

	@BeforeEach
	void setUp(TestInfo testInfo) throws Exception {
		String txt = MessageFormat.format("Before : {0} : {1} : {2}", testInfo.getTestClass(), testInfo.getTestMethod(),
				testInfo.getDisplayName());
		System.out.println(txt);
	}
	
	@AfterEach
	void tearDown(TestInfo testInfo) throws Exception {
		String txt = MessageFormat.format("After : {0} : {1} : {2}", testInfo.getTestClass(), testInfo.getTestMethod(),
				testInfo.getDisplayName());
		System.out.println(txt);
	}

	private ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	private HandlerExceptionResolver exceptionResolver() {
		SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

		Properties exceptionMappings = new Properties();
		exceptionMappings.put("java.lang.Exception", "error/error");
		exceptionMappings.put("java.lang.RuntimeException", "error/error");
		exceptionResolver.setExceptionMappings(exceptionMappings);

		Properties statusCodes = new Properties();
		statusCodes.put("error/404", "404");
		statusCodes.put("error/error", "500");

		exceptionResolver.setStatusCodes(statusCodes);
		return exceptionResolver;
	}

}
