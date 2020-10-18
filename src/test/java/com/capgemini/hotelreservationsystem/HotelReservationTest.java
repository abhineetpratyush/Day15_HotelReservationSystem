package com.capgemini.hotelreservationsystem;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.capgemini.hotelreservationsystem.HotelReservation.CustomerType;

public class HotelReservationTest {
	private static final Logger log = LogManager.getLogger(HotelReservationTest.class);

	@Test
	public void given3HotelDetails_WhenAdded_ShouldReturnCount3() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);
		log.info(hotelReservation.hotelList);
		int noOfHotelsAdded = hotelReservation.countNoOfHotels();
		Assert.assertEquals(3, noOfHotelsAdded);
	}

	@Test
	public void given3Hotels_InAGivenDateRange_ShouldReturnCheapestHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);
		String cheapestHotelInfo = hotelReservation.getCheapestHotel("10 Sep 2020", "11 Sep 2020");
		log.info(cheapestHotelInfo);
		Assert.assertEquals("Lakewood Total Cost: $220", cheapestHotelInfo);
	}

	@Test
	public void given3Hotels_WhenWeekdayAndWeekendRatesAdded_ShouldReturnThoseRates() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);
		List<Integer> weekendRoomRates = new ArrayList<>();
		List<Integer> weekdayRoomRates = new ArrayList<>();
		hotelReservation.hotelList.stream().forEach(hotelDetails -> {
			weekendRoomRates.add(hotelDetails.getWeekendRoomRateRegular());
			weekdayRoomRates.add(hotelDetails.getWeekdayRoomRateRegular());
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
		hotelReservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);
		String cheapestHotelInfo = hotelReservation.getCheapestHotel("11 Sep 2020", "12 Sep 2020");
		log.info(cheapestHotelInfo);
		Assert.assertEquals("Lakewood, Bridgewood Total Cost: $200", cheapestHotelInfo);
	}

	@Test
	public void given3Hotels_WhenRatingsAdded_ShouldReturnRates() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);
		List<Integer> ratingList = new ArrayList<>();
		hotelReservation.hotelList.stream().forEach(hotelDetails -> {
			ratingList.add(hotelDetails.getRating());
		});
		Assert.assertEquals(3, (int) ratingList.get(0));
		Assert.assertEquals(4, (int) ratingList.get(1));
		Assert.assertEquals(5, (int) ratingList.get(2));
	}

	@Test
	public void given3Hotels_InAGivenDateRange_ShouldReturnCheapestBestRatedHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);
		String cheapestBestRatedHotelInfo = hotelReservation.getCheapestBestRatedHotel("11 Sep 2020", "12 Sep 2020");
		log.info(cheapestBestRatedHotelInfo);
		Assert.assertEquals("Bridgewood, Rating: 4, Total Cost: $200", cheapestBestRatedHotelInfo);
	}

	@Test
	public void given3Hotels_InAGivenDateRange_ShouldReturnBestRatedHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);
		String bestRatedHotelInfo = hotelReservation.getBestRatedHotel("11 Sep 2020", "12 Sep 2020");
		log.info(bestRatedHotelInfo);
		Assert.assertEquals("Ridgewood, Total Cost: $370", bestRatedHotelInfo);
	}	

	@Test
	public void given3Hotels_WhenRatesForRewardsCustomerAdded_ShouldReturnTheRates() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);
		List<Integer> weekdayRateListRewards = new ArrayList<>();
		List<Integer> weekendRateListRewards = new ArrayList<>();
		hotelReservation.hotelList.stream().forEach(hotelDetails -> {
			weekdayRateListRewards.add(hotelDetails.getWeekdayRoomRateRewards());
		});
		hotelReservation.hotelList.stream().forEach(hotelDetails -> {
			weekendRateListRewards.add(hotelDetails.getWeekendRoomRateRewards());
		});
		Assert.assertEquals(80, (int) weekdayRateListRewards.get(0));
		Assert.assertEquals(110, (int) weekdayRateListRewards.get(1));
		Assert.assertEquals(100, (int) weekdayRateListRewards.get(2));
		Assert.assertEquals(80, (int) weekendRateListRewards.get(0));
		Assert.assertEquals(50, (int) weekendRateListRewards.get(1));
		Assert.assertEquals(40, (int) weekendRateListRewards.get(2));
	}
	
	@Test
	public void given3Hotels_WhenDateRangeProvidedForRewardsCustomer_ShouldReturnCheapestBestRatedHotel() {
		HotelReservation hotelReservation = new HotelReservation();
		hotelReservation.addHotel("Lakewood", 110, 90, 80, 80, 3);
		hotelReservation.addHotel("Bridgewood", 150, 50, 110, 50, 4);
		hotelReservation.addHotel("Ridgewood", 220, 150, 100, 40, 5);
		try{
			hotelReservation.typeOfCustomer("REWARDS");
		}
		catch(CustomerTypeException e) {
			e.printStackTrace();
		}
		String cheapestBestRatedHotelForRewards = hotelReservation.cheapestBestRatedHotelSelector("11 Sep 2020", "12 Sep 2020");
		log.info(cheapestBestRatedHotelForRewards);
		Assert.assertEquals("Ridgewood, Rating: 5, Total Cost: $140", cheapestBestRatedHotelForRewards);
	}		
}
