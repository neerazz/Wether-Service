package com.neeraj.assignment.security;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.neeraj.assignment.model.CustomUserDetails;
import com.neeraj.assignment.model.TokenUserDetails;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private CustomAuthenticationValidator validator;

	@Autowired
	private CustomUserDetails customUserDetails;

	private final Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		log.trace("additionalAuthenticationChecks");
	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

		log.trace("retrieveUser");

		CustomAuthenticationToken jwtAuthenticationToken = (CustomAuthenticationToken) usernamePasswordAuthenticationToken;
		String token = jwtAuthenticationToken.getToken();

		TokenUserDetails jwtUser = validator.validate(token);

		if (jwtUser == null) {
			throw new RuntimeException("Invalid Token entered. Enter a valid token.");
		}

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(jwtUser.getRole());

		return customUserDetails.setUserName(jwtUser.getUserName()).setUserID(jwtUser.getId()).setToken(token)
				.setAuthorities(grantedAuthorities);
	}

	@Override
	public boolean supports(Class<?> aClass) {
		log.trace("supports");
		return (CustomAuthenticationToken.class.isAssignableFrom(aClass));
	}
}