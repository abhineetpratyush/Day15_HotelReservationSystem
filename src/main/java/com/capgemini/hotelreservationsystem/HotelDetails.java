package com.capgemini.hotelreservationsystem;

public class HotelDetails {
	private String hotelName;
	private int roomRate;
	
	public HotelDetails(String hotelName, int roomRate) {
		this.hotelName = hotelName;
		this.roomRate = roomRate;
	}

	@Override
	public String toString() {
		return "HotelDetails [hotelName=" + hotelName + ", roomRate=" + roomRate + "]";
	}
}
