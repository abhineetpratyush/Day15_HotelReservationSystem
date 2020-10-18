package com.capgemini.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	 * uc5 : add ability to take in ratings for the hotels
	 * @param hotelName
	 * @param roomRate
	 */
	public void addHotel(String hotelName, int weekdayRoomRate, int weekendRoomRate, int rating) {
		HotelDetails hotelDetails = new HotelDetails(hotelName, weekdayRoomRate, weekendRoomRate, rating);  
		this.hotelList.add(hotelDetails);
	}

	public int countNoOfHotels() {
		return hotelList.size();
	}

	/**
	 * uc2 : get cheapest hotel in a given date range
	 * uc4: get cheapest hotel in a given date range considering weekday and weekend
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String getCheapestHotel(String startDate, String endDate) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
		LocalDate startDateInput = LocalDate.parse(startDate, dateFormat);
		LocalDate endDateInput = LocalDate.parse(endDate, dateFormat);
		int noOfDaysToBook = (int) ChronoUnit.DAYS.between(startDateInput, endDateInput) + 1;
		List<DayOfWeek> daysList = new ArrayList<>();
		daysList = Stream.iterate(startDateInput.getDayOfWeek(), day -> day.plus(1)).limit(noOfDaysToBook).collect(Collectors.toList());
		int noOfWeekends = (int) daysList.stream().filter(day -> 
		day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)).count();
		int noOfWeekdays = daysList.size() - noOfWeekends;
		int minCost = hotelList.get(0).getWeekdayRoomRate() * noOfWeekdays + hotelList.get(0).getWeekendRoomRate() * noOfWeekends ;
		List<String> cheapestHotelNameList = new ArrayList<>();
		cheapestHotelNameList.add(hotelList.get(0).getHotelName());
		//for and if used because we can have multiple hotels offering the same lowest total price until uc4
		for(int i = 1; i < hotelList.size(); i++) {
			if(hotelList.get(i).getWeekdayRoomRate() * noOfWeekdays + hotelList.get(i).getWeekendRoomRate() * noOfWeekends < minCost) {
				minCost = hotelList.get(i).getWeekdayRoomRate() * noOfWeekdays + hotelList.get(i).getWeekendRoomRate() * noOfWeekends;
				for(int j = 0; j < cheapestHotelNameList.size(); j++) 
					cheapestHotelNameList.remove(j);
				cheapestHotelNameList.add(hotelList.get(i).getHotelName());
			}
			if(hotelList.get(i).getWeekdayRoomRate() * noOfWeekdays + hotelList.get(i).getWeekendRoomRate() * noOfWeekends == minCost)
				cheapestHotelNameList.add(hotelList.get(i).getHotelName());
		}
		String hotelNameString = cheapestHotelNameList.stream().collect(Collectors.joining(", "));
		String cheapestHotelInfo = hotelNameString + " Total Cost: $" + minCost;
		return cheapestHotelInfo;
	}

	/**
	 * uc6 : get cheapest best rated hotel for a given date range
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String getCheapestBestRatedHotel(String startDate, String endDate) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
		LocalDate startDateInput = LocalDate.parse(startDate, dateFormat);
		LocalDate endDateInput = LocalDate.parse(endDate, dateFormat);
		int noOfDaysToBook = (int) ChronoUnit.DAYS.between(startDateInput, endDateInput) + 1;
		List<DayOfWeek> daysList = new ArrayList<>();
		daysList = Stream.iterate(startDateInput.getDayOfWeek(), day -> day.plus(1)).limit(noOfDaysToBook).collect(Collectors.toList());
		int noOfWeekends = (int) daysList.stream().filter(day -> 
		day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)).count();
		int noOfWeekdays = daysList.size() - noOfWeekends;
		int minCost = hotelList.get(0).getWeekdayRoomRate() * noOfWeekdays + hotelList.get(0).getWeekendRoomRate() * noOfWeekends ;
		List<HotelDetails> cheapestHotelList = new ArrayList<>();
		cheapestHotelList.add(hotelList.get(0));
		//for and if used because we can have multiple hotels offering the same lowest total price until uc4
		for(int i = 1; i < hotelList.size(); i++) {
			if(hotelList.get(i).getWeekdayRoomRate() * noOfWeekdays + hotelList.get(i).getWeekendRoomRate() * noOfWeekends < minCost) {
				minCost = hotelList.get(i).getWeekdayRoomRate() * noOfWeekdays + hotelList.get(i).getWeekendRoomRate() * noOfWeekends;
				for(int j = 0; j < cheapestHotelList.size(); j++) 
					cheapestHotelList.remove(j);
				cheapestHotelList.add(hotelList.get(i));
			}
			if(hotelList.get(i).getWeekdayRoomRate() * noOfWeekdays + hotelList.get(i).getWeekendRoomRate() * noOfWeekends == minCost)
				cheapestHotelList.add(hotelList.get(i));
		}
		HotelDetails cheapestBestRatedHotel = cheapestHotelList.stream().max((hotelOne, hotelTwo) -> hotelOne.getRating() - hotelTwo.getRating()).orElse(null);
		String cheapestBestRatedHotelInfo = cheapestBestRatedHotel.getHotelName() + ", Rating: " + cheapestBestRatedHotel.getRating() + ", Total Cost: $" + minCost;
		return cheapestBestRatedHotelInfo;
	}

	/**
	 * uc7 : get best rated hotel for the given date range
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String getBestRatedHotel(String startDate, String endDate) {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
		LocalDate startDateInput = LocalDate.parse(startDate, dateFormat);
		LocalDate endDateInput = LocalDate.parse(endDate, dateFormat);
		int noOfDaysToBook = (int) ChronoUnit.DAYS.between(startDateInput, endDateInput) + 1;
		List<DayOfWeek> daysList = new ArrayList<>();
		daysList = Stream.iterate(startDateInput.getDayOfWeek(), day -> day.plus(1)).limit(noOfDaysToBook).collect(Collectors.toList());
		int noOfWeekends = (int) daysList.stream().filter(day -> 
		day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)).count();
		int noOfWeekdays = daysList.size() - noOfWeekends;
		HotelDetails bestRatedHotel = hotelList.stream().max((hotelOne, hotelTwo) -> hotelOne.getRating() - hotelTwo.getRating()).orElse(null);
		int bestRatedCost = bestRatedHotel.getWeekdayRoomRate() * noOfWeekdays + bestRatedHotel.getWeekendRoomRate() * noOfWeekends;
		String bestRatedHotelInfo = bestRatedHotel.getHotelName() + ", Total Cost: $" + bestRatedCost;
		return bestRatedHotelInfo;
	}

	public static void main(String[] args) {
		log.info("Welcome to Hotel Reservation Program");
	}
}
