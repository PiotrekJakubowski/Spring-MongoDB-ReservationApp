package com.mongodb.model;

public class Reservation {

	private String court;
	private String date;

	public Reservation() {

	}

	public Reservation(String court, String date) {
		super();
		this.court = court;
		this.date = date;
	}

	public String getCourt() {
		return court;
	}

	public void setCourt(String court) {
		this.court = court;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Reservation [court=" + court + ", date=" + date + "]";
	}

}
