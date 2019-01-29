package com.neeraj.assignment.model;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@Scope("prototype")
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String userName;
	private String token;
	private String userID;
	private Collection<? extends GrantedAuthority> authorities;

	public CustomUserDetails() {
		super();
	}

	public CustomUserDetails(String userName, String id, String token, List<GrantedAuthority> grantedAuthorities) {
		this.userName = userName;
		this.userID = id;
		this.token = token;
		this.authorities = grantedAuthorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUserName() {
		return userName;
	}

	public String getToken() {
		return token;
	}

	public String getUserID() {
		return userID;
	}

	public CustomUserDetails setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
		return this;
	}

	/**
	 * @param userName the userName to set
	 */
	public CustomUserDetails setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	/**
	 * @param token the token to set
	 */
	public CustomUserDetails setToken(String token) {
		this.token = token;
		return this;
	}

	/**
	 * @param userID the userID to set
	 */
	public CustomUserDetails setUserID(String userID) {
		this.userID = userID;
		return this;
	}
}
