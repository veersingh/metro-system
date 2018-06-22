package com.sapient.metrosystem.model;

public class SmartCard {

	private int id;
	private double balance;
	private User user;
	public SmartCard(int id, double balance, User user) {
		super();
		this.id = id;
		this.balance = balance;
		this.user = user;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SmartCard [id=");
		builder.append(id);
		builder.append(", balance=");
		builder.append(balance);
		builder.append(", user=");
		builder.append(user);
		builder.append("]");
		return builder.toString();
	}
	
	
}
