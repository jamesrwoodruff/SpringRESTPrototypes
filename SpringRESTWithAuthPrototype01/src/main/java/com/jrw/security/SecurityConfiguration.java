package com.jrw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * The authentication approach used here is HTTP Basic Authentication, one of the simplest, but not the most secure, approach.
 * Specifically, this example uses Spring Security to configure the HTTP Basic Authentication. 
 * This approach involves sending credentials with each request using an HTTP header ("Authorization").
 * State is not maintained on the server.
 * The simplicity of this approach is a drawback in that users’ passwords be obtained by a third party observing network traffic. 
 * Therefore, it should only be used over a secure transport layer, such as HTTPS. (Caliskan 353)
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private static String REALM="MY_TEST_REALM";
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user1-admin").password("monkey1").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("user2-user").password("monkey2").roles("USER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http.csrf().disable()
	  	.authorizeRequests()
	  	.antMatchers("/book/**").hasRole("ADMIN")
		.and()
		.httpBasic()
		.realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint());
 	}
	
	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		return new CustomBasicAuthenticationEntryPoint();
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
