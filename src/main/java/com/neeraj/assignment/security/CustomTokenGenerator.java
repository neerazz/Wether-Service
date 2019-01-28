package com.neeraj.assignment.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.neeraj.assignment.model.TokenUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@PropertySource({ "classpath:application.properties" })
public class CustomTokenGenerator {

	@Value("${application.secret.key}")
	private String secretKey;

	private final Logger log = LoggerFactory.getLogger(CustomTokenGenerator.class);

	public String generate(TokenUserDetails jwtUser) {

		log.trace("generate");

		Claims claims = Jwts.claims().setSubject(jwtUser.getUserName());
		claims.put("userId", String.valueOf(jwtUser.getId()));
		claims.put("role", jwtUser.getRole());

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}
}