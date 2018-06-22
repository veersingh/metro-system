package com.sapient.metrosystem.model;

public class User {

	private int userid;
	private String name;
	public User(int userid, String name) {
		super();
		this.userid = userid;
		this.name = name;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userid=");
		builder.append(userid);
		builder.append(", name=");
		builder.append(name);
		builder.append("]");
		return builder.toString();
	}
	
	
}
