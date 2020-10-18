package com.capgemini.hotelreservationsystem;

public class HotelDetails {
	private String hotelName;
	private int weekdayRoomRate;
	private int weekendRoomRate;

	public HotelDetails(String hotelName, int weekdayRoomRate, int weekendRoomRate) {
		this.hotelName = hotelName;
		this.weekdayRoomRate = weekdayRoomRate;
		this.weekendRoomRate = weekendRoomRate;
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

	@Override
	public String toString() {
		return "HotelDetails [hotelName=" + hotelName + ", weekdayRoomRate=" + weekdayRoomRate + ", weekendRoomRate="
				+ weekendRoomRate + "]";
	}
}
