package com.mongodb.model;

public class Court {

	private String type;

	private String sector;

	public Court() {
	}

	public Court(String type, String sector) {
		super();
		this.type = type;
		this.sector = sector;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	@Override
	public String toString() {
		return "Court [type=" + type + ", sector=" + sector + "]";
	}

}
