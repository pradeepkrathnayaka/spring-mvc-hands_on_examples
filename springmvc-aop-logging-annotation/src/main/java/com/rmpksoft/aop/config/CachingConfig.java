package com.rmpksoft.aop.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class CachingConfig {
	
	static {
		System.out.println("CachingConfig...................................................");
	}

	@Lazy
	@Bean(name="cacheManager")
	public ConcurrentMapCacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}

	@Lazy
	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
		factory.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factory.setShared(true);
		return factory;
	}

}
