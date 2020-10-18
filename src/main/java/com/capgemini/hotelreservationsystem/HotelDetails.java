package com.capgemini.hotelreservationsystem;

public class HotelDetails {
	private String hotelName;
	private int weekdayRoomRate;
	private int weekendRoomRate;
	private int rating;

	public HotelDetails(String hotelName, int weekdayRoomRate, int weekendRoomRate, int rating) {
		this.hotelName = hotelName;
		this.weekdayRoomRate = weekdayRoomRate;
		this.weekendRoomRate = weekendRoomRate;
		this.rating = rating;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public int getWeekdayRoomRate() {
		return weekdayRoomRate;
	}

	public void setWeekdayRoomRate(int weekdayRoomRate) {
		this.weekdayRoomRate = weekdayRoomRate;
	}

	public int getWeekendRoomRate() {
		return weekendRoomRate;
	}

	public void setWeekendRoomRate(int weekendRoomRate) {
		this.weekendRoomRate = weekendRoomRate;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "HotelDetails [hotelName=" + hotelName + ", weekdayRoomRate=" + weekdayRoomRate + ", weekendRoomRate="
				+ weekendRoomRate + ", rating=" + rating + "]";
	}
}
