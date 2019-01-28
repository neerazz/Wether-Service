package com.neeraj.assignment.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.neeraj.assignment.exception.InvalidSecreatKeyException;
import com.neeraj.assignment.model.FrontEndUserModel;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@PropertySource({ "classpath:application.properties" })
public class CustomTokenGenerator {

	@Value("${application.secret.key}")
	private String secretKey;

	private final Logger log = LoggerFactory.getLogger(CustomTokenGenerator.class);

	public String generate(FrontEndUserModel inputUser) {

		log.trace("generate");
		validateSecreatKey(inputUser.getSecreatKey());
		Claims claims = Jwts.claims().setSubject(inputUser.getUserName());
		claims.put("userId", String.valueOf(inputUser.getUserId()));
		claims.put("role", inputUser.getRole());
		String newToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact();
		log.info("New Token : '" + newToken + "' generated for user" + inputUser);
		return newToken;
	}

	private void validateSecreatKey(String inputSecreatKey) {
		log.trace("validateSecreatKey with input as:" + inputSecreatKey);
		if (!secretKey.equalsIgnoreCase(inputSecreatKey)) {
			throw new InvalidSecreatKeyException(
					"The entered Secreat key:'" + inputSecreatKey + "' is invalid. Enter Valid Secreat key");
		}
	}
}