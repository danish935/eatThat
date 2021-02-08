package com.eatThat.application.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eatThat.application.util.HttpLoggingFilter;

@Configuration
public class AppConfig  {

	
	@Bean
	public FilterRegistrationBean someFilterRegistration() {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(loggingFilter());
	    registration.addUrlPatterns("/app/api/*");
	    registration.addInitParameter("paramName", "paramValue");
	    registration.setName("someFilter");
	    registration.setOrder(1);
	    return registration;
	}
	
	public HttpLoggingFilter loggingFilter() {
	    HttpLoggingFilter httpLoggingFilter = new HttpLoggingFilter();
	    return httpLoggingFilter;
	}
}