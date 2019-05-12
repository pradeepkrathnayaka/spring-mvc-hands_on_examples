package com.rmpksoft.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.rmpksoft.aop.service" })
public class ServiceConfig {
	
	static {
		System.out.println("ServiceConfig........................................");
	}

}
