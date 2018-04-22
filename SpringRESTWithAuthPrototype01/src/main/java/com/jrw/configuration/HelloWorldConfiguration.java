package com.jrw.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// see http://websystique.com/spring-security/secure-spring-rest-api-using-basic-authentication/
// see http://stackoverflow.com/questions/35218354/difference-between-registerglobal-configure-configureglobal-configureglo
// see http://docs.spring.io/spring-security/site/docs/current/reference/html/jc.html

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.jrw")
public class HelloWorldConfiguration {
}