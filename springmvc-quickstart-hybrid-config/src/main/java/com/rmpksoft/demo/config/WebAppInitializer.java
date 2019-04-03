package com.rmpksoft.demo.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer {

	/**
	 * time of inactivity after which session should be invalidated
	 */
	private static final int maxInactiveInterval = 60 * 60 * 2; // 2 hours

	@Override
	public void onStartup(ServletContext servletContext) {
		XmlWebApplicationContext appContext = new XmlWebApplicationContext();
		appContext.setConfigLocation("classpath:spring/app-config.xml");

		// set session timeout
		servletContext.addListener(new SessionListener(maxInactiveInterval));
		// set dispatcher servlet and mapping
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
				new DispatcherServlet(appContext));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);
	}

}
