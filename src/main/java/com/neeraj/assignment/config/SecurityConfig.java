package com.neeraj.assignment.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.neeraj.assignment.security.CustomAuthenticationEntryPoint;
import com.neeraj.assignment.security.CustomAuthenticationFailureHandler;
import com.neeraj.assignment.security.CustomAuthenticationProvider;
import com.neeraj.assignment.security.CustomAuthenticationSucessHandler;
import com.neeraj.assignment.security.CustomAuthenticationTokenFilter;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomAuthenticationProvider authenticationProvider;
	@Autowired
	private CustomAuthenticationEntryPoint entryPoint;
	@Autowired
	private CustomAuthenticationSucessHandler sucessHandler;
	@Autowired
	private CustomAuthenticationFailureHandler failureHandler;

	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}

	@Bean
	public CustomAuthenticationTokenFilter authenticationTokenFilter() {
		CustomAuthenticationTokenFilter filter = new CustomAuthenticationTokenFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(sucessHandler);
//		filter.setAuthenticationFailureHandler(failureHandler);
		return filter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("**/rest/**").authenticated().and().exceptionHandling()
				.authenticationEntryPoint(entryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();
	}

}