package com.neeraj.assignment.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TokenUserDetails {
	private String userName;
	private String userId;
	private String role;

	public TokenUserDetails setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public TokenUserDetails setId(String id) {
		this.userId = id;
		return this;
	}

	public TokenUserDetails setRole(String role) {
		this.role = role;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public String getId() {
		return userId;
	}

	public String getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "TokenUserDetails [userName=" + userName + ", userId=" + userId + ", role=" + role + "]";
	}
}