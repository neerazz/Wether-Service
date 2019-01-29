package com.neeraj.assignment.security;

import com.neeraj.assignment.model.TokenUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({ "classpath:application.properties" })
public class CustomAuthenticationValidator {

	@Autowired
	private TokenUserDetails tokenUserDetails;

	@Value("${application.secret.key}")
	private String secretKey;

	private final Logger log = LoggerFactory.getLogger(CustomAuthenticationValidator.class);

	public TokenUserDetails validate(String token) {

		log.trace("validate");
		Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
		tokenUserDetails.setUserName(body.getSubject())
				.setId((String) body.get("userId"))
				.setRole((String) body.get("role"));

		return tokenUserDetails;
	}
}