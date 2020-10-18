package com.capgemini.hotelreservationsystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class HotelReservationTest {
	private static final Logger log = LogManager.getLogger(HotelReservationTest.class);

	@Test
	public void given3HotelDetails_WhenAdded_ShouldReturnCount3() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110);
		hotelReservation.addHotel("Bridgewood", 160);
		hotelReservation.addHotel("Ridgewood", 220);
		log.info(hotelReservation.hotelList);
		int noOfHotelsAdded = hotelReservation.countNoOfHotels();
		Assert.assertEquals(3, noOfHotelsAdded);
	}
}
