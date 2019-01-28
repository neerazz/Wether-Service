package com.neeraj.assignment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neeraj.assignment.model.TokenUserDetails;
import com.neeraj.assignment.security.CustomTokenGenerator;

@RestController
@RequestMapping("/token")
public class GenerateTokenController {

	private CustomTokenGenerator jwtGenerator;

	public GenerateTokenController(CustomTokenGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping
	public String generate(@RequestBody final TokenUserDetails jwtUser) {
		return jwtGenerator.generate(jwtUser);

	}
}