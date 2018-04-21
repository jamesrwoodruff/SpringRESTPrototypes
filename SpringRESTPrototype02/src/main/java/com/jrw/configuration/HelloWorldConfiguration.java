package com.jrw.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// see http://websystique.com/springmvc/spring-mvc-4-restful-web-services-crud-example-resttemplate/

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.jrw")
public class HelloWorldConfiguration {
}