package com.setplex.ott.subscription.model;

public class UserResponce {

	User user;
	Object userPackage;

	public UserResponce(User user, Object userPackage) {
		this.user = user;
		this.userPackage = userPackage;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Object getUserPackage() {
		return userPackage;
	}

	public void setUserPackage(Object userPackage) {
		this.userPackage = userPackage;
	}

}