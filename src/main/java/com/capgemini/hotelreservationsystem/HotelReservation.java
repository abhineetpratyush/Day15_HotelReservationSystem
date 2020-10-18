package com.capgemini.hotelreservationsystem;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservation {
	private static final Logger log = LogManager.getLogger(HotelReservation.class);
	public List<HotelDetails> hotelList;
	
	public HotelReservation() {
		hotelList = new ArrayList<>();
	}
	
	public void addHotel(String hotelName, int roomRate) {
		HotelDetails hotelDetails = new HotelDetails(hotelName, roomRate);  
		this.hotelList.add(hotelDetails);
	}
	
	public int countNoOfHotels() {
		return hotelList.size();
	}

	public static void main(String[] args) {
		log.info("Welcome to Hotel Reservation Program");
	}
}
