package com.mom.mongo;

public class Message {

	private double[] location;
	private String message;

	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(double[] location, String message) {
		this.location = location;
		this.message = message;
	}

	public double[] getLocation() {
		return location;
	}

	public void setLocation(double[] location) {
		this.location = location;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
