package com.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reservations")
public class Reservation {

	@Id
	private String id;
	
	private String court;
	private String date;

	public Reservation() {

	}

	public Reservation(String court, String date) {
		super();
		this.court = court;
		this.date = date;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		return "Reservation [id=" +id + ", court=" + court + ", date=" + date + "]";
	}

}
