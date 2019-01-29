package com.neeraj.assignment.model;


public class FrontEndUserModel {

	private String userName;
	private String userId;
	private String role;
	private String secreatKey;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public FrontEndUserModel setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public FrontEndUserModel setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public FrontEndUserModel setRole(String role) {
		this.role = role;
		return this;
	}

	/**
	 * @return the secreatKey
	 */
	public String getSecreatKey() {
		return secreatKey;
	}

	/**
	 * @param secreatKey the secreatKey to set
	 */
	public FrontEndUserModel setSecreatKey(String secreatKey) {
		this.secreatKey = secreatKey;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FrontEndUserModel [userName=" + userName + ", userId=" + userId + ", role=" + role + ", secreatKey="
				+ secreatKey + "]";
	}
}