package com.mongodb.model;

public class Reservation {

	private Court court;
	private String date;

	public Reservation() {

	}

	public Reservation(Court court, String date) {
		super();
		this.court = court;
		this.date = date;
	}

	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
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
