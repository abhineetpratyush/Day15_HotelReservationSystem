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
	
	@Test
	public void given3Hotels_InAGivenDateRange_ShouldReturnCheapestHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110);
		hotelReservation.addHotel("Bridgewood", 160);
		hotelReservation.addHotel("Ridgewood", 220);
		String cheapestHotelInfo = hotelReservation.getCheapestHotel("10 Sep 2020", "11 Sep 2020");
		log.info(cheapestHotelInfo);
		Assert.assertEquals("Lakewood, Total Cost: $220", cheapestHotelInfo);
	}
}
