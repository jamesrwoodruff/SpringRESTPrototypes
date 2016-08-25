package com.jrw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/*
 * @SpringBootApplication is a convenience annotation that is equivalent to declaring
 * 	@Configuration indicates the class declares one or more @Bean definitions
 * 	@EnableAutoConfiguration tells Spring Boot to guess and configure beans you are likely to need.
 * 		Normally you would add @EnableWebMvc for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. 
 * 	@ComponentScan tells Spring to look for other components.
*/
@SpringBootApplication
public class SpringRestPrototype01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestPrototype01Application.class, args);
	}
}
