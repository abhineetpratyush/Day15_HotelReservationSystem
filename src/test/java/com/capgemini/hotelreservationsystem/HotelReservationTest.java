package com.capgemini.hotelreservationsystem;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class HotelReservationTest {
	private static final Logger log = LogManager.getLogger(HotelReservationTest.class);

	@Test
	public void given3HotelDetails_WhenAdded_ShouldReturnCount3() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90);
		hotelReservation.addHotel("Bridgewood", 160, 60);
		hotelReservation.addHotel("Ridgewood", 220, 150);
		log.info(hotelReservation.hotelList);
		int noOfHotelsAdded = hotelReservation.countNoOfHotels();
		Assert.assertEquals(3, noOfHotelsAdded);
	}

	@Test
	public void given3Hotels_InAGivenDateRange_ShouldReturnCheapestHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90);
		hotelReservation.addHotel("Bridgewood", 150, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150);
		String cheapestHotelInfo = hotelReservation.getCheapestHotel("10 Sep 2020", "11 Sep 2020");
		log.info(cheapestHotelInfo);
		Assert.assertEquals("Lakewood Total Cost: $220", cheapestHotelInfo);
	}

	@Test
	public void given3Hotels_WhenWeekdayAndWeekendRatesAdded_ShouldReturnThoseRates() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90);
		hotelReservation.addHotel("Bridgewood", 150, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150);
		List<Integer> weekendRoomRates = new ArrayList<>();
		List<Integer> weekdayRoomRates = new ArrayList<>();
		hotelReservation.hotelList.stream().forEach(hotelDetails -> {
			weekendRoomRates.add(hotelDetails.getWeekendRoomRate());
			weekdayRoomRates.add(hotelDetails.getWeekdayRoomRate());
		});
		Assert.assertEquals(110, (int) weekdayRoomRates.get(0));
		Assert.assertEquals(150, (int) weekdayRoomRates.get(1));
		Assert.assertEquals(220, (int) weekdayRoomRates.get(2));
		Assert.assertEquals(90, (int) weekendRoomRates.get(0));
		Assert.assertEquals(50, (int) weekendRoomRates.get(1));
		Assert.assertEquals(150, (int) weekendRoomRates.get(2));
	}
	
	@Test
	public void given3Hotels_InAGivenDateRange_ShouldReturnCheapestHotelBasedOnWeekdayAndWeekend() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90);
		hotelReservation.addHotel("Bridgewood", 150, 50);
		hotelReservation.addHotel("Ridgewood", 220, 150);
		String cheapestHotelInfo = hotelReservation.getCheapestHotel("11 Sep 2020", "12 Sep 2020");
		log.info(cheapestHotelInfo);
		Assert.assertEquals("Lakewood, Bridgewood Total Cost: $200", cheapestHotelInfo);
	}
}
