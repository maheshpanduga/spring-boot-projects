package com.mahi.model;

import java.util.List;

public class Atm {
	private Address address;
	private int distance;
	private String functionality;
	private String type;
	private List<OpeningHour> openingHours;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public String getFunctionality() {
		return functionality;
	}

	public void setFunctionality(String functionality) {
		this.functionality = functionality;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<OpeningHour> getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(List<OpeningHour> openingHours) {
		this.openingHours = openingHours;
	}
}
