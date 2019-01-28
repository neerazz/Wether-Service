package com.neeraj.assignment.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private final Logger log = LoggerFactory.getLogger(CustomAuthenticationToken.class);

	private String secreatToken;

	public CustomAuthenticationToken() {
		super(null, null);
		log.trace("JwtAuthenticationToken");
	}

	public String getToken() {
		return secreatToken;
	}

	public CustomAuthenticationToken setToken(String token) {
		this.secreatToken = token;
		return this;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}
}