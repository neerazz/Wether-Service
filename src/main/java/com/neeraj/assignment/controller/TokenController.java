package com.neeraj.assignment.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neeraj.assignment.model.FrontEndUserModel;
import com.neeraj.assignment.security.CustomTokenGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private CustomTokenGenerator jwtGenerator;
	private static final Logger logger = LoggerFactory.getLogger(APIController.class);

	@PostMapping
	public String generateToken(@RequestBody final FrontEndUserModel inputUserDetails, HttpServletRequest req) {
		logger.trace("generateToken for user:" + inputUserDetails);
		return jwtGenerator.generate(inputUserDetails);
	}
}