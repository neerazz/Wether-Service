package com.neeraj.assignment.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private CustomAuthenticationToken token;

	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

	private final Logger log = LoggerFactory.getLogger(CustomAuthenticationTokenFilter.class);

	public CustomAuthenticationTokenFilter() {
		super("/api/**");
		log.trace("JwtAuthenticationTokenFilter");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

		log.trace("attemptAuthentication");

		String header = httpServletRequest.getHeader("Authorisation");

		String authenticationToken = null;

		if (header != null && header.startsWith("Token ")) {
			authenticationToken = header.substring(6);
		}

		return getAuthenticationManager().authenticate(token.setToken(authenticationToken));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		super.successfulAuthentication(request, response, chain, authResult);
		log.trace("successfulAuthentication");
		chain.doFilter(request, response);
	}

	@Override
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
		super.setAuthenticationFailureHandler(customAuthenticationFailureHandler);
	}
}
