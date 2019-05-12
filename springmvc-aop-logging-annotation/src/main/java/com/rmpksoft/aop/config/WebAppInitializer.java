package com.rmpksoft.aop.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInitializer implements WebApplicationInitializer{
	static {
		System.out.println("WebAppInitializer........................................");
	}

	@Override
	public void onStartup(final ServletContext servletContext) throws ServletException {

		// register config classes
		WebApplicationContext rootContext = getContext();
		servletContext.addListener(new ContextLoaderListener(rootContext));
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(rootContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

		ServletRegistration.Dynamic dispatcher = servletContext.addServlet("Dispatcher", dispatcherServlet);
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

	}
	
	private AnnotationConfigWebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(WebMvcConfig.class);
		context.register(ServiceConfig.class);
		return context;
	}
	
}
