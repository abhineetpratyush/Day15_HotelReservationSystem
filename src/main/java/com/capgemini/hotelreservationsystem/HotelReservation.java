package com.capgemini.hotelreservationsystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservation {
	private static final Logger log = LogManager.getLogger(HotelReservation.class);
	public List<HotelDetails> hotelList;

	public HotelReservation() {
		hotelList = new ArrayList<>();
	}

	/**
	 * uc1 : add hotel with name and rate for regular customer
	 * uc3 : add ability to take in both separate weekday and weekend room rates
	 * @param hotelName
	 * @param roomRate
	 */
	public void addHotel(String hotelName, int weekdayRoomRate, int weekendRoomRate) {
		HotelDetails hotelDetails = new HotelDetails(hotelName, weekdayRoomRate, weekendRoomRate);  
		this.hotelList.add(hotelDetails);
	}

	public int countNoOfHotels() {
		return hotelList.size();
	}

	/**
	 * uc2 : get cheapest hotel in a given date range
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String getCheapestHotel(String startDate, String endDate) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
		LocalDate startDateInput = LocalDate.parse(startDate, dateFormat);
		LocalDate endDateInput = LocalDate.parse(endDate, dateFormat);
		int noOfDaysToBook = (int) ChronoUnit.DAYS.between(startDateInput, endDateInput) + 1;
		Map<String, Integer> hotelNameToTotalCostMap = hotelList.stream().collect(Collectors.toMap(hotel -> hotel.getHotelName(), hotel -> hotel.getWeekdayRoomRate() * noOfDaysToBook));
		String cheapestHotelName = hotelNameToTotalCostMap.keySet().stream().min((hotel1, hotel2) -> hotelNameToTotalCostMap.get(hotel1) - hotelNameToTotalCostMap.get(hotel2)).orElse(null);
		String cheapestHotelInfo = cheapestHotelName + ", Total Cost: $" + hotelNameToTotalCostMap.get(cheapestHotelName);
		return cheapestHotelInfo;
	}

	public static void main(String[] args) {
		log.info("Welcome to Hotel Reservation Program");
	}
}
