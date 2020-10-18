package com.capgemini.hotelreservationsystem;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HotelReservation {
	public enum CustomerType {
		REGULAR, REWARDS
	}
	private CustomerType typeOfCustomer;
	private static final Logger log = LogManager.getLogger(HotelReservation.class);
	public List<HotelDetails> hotelList;

	public HotelReservation() {
		hotelList = new ArrayList<>();
	}

	/**
	 * uc1 : add hotel with name and rate for regular customer
	 * uc3 : add ability to take in both separate weekday and weekend room rates
	 * uc5 : add ability to take in ratings for the hotels
	 * uc8 : add ability to take in special rates for rewards customers
	 * @param hotelName
	 * @param roomRate
	 */
	public void addHotel(String hotelName, int weekdayRoomRateRegular, int weekendRoomRateRegular, int weekdayRoomRateRewards, int weekendRoomRateRewards, int rating) {
		HotelDetails hotelDetails = new HotelDetails(hotelName, weekdayRoomRateRegular, weekendRoomRateRegular, weekdayRoomRateRewards, weekendRoomRateRewards, rating);  
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
		int minCost = hotelList.get(0).getWeekdayRoomRateRegular() * noOfWeekdays + hotelList.get(0).getWeekendRoomRateRegular() * noOfWeekends ;
		List<String> cheapestHotelNameList = new ArrayList<>();
		cheapestHotelNameList.add(hotelList.get(0).getHotelName());
		//for and if used because we can have multiple hotels offering the same lowest total price until uc4
		for(int i = 1; i < hotelList.size(); i++) {
			if(hotelList.get(i).getWeekdayRoomRateRegular() * noOfWeekdays + hotelList.get(i).getWeekendRoomRateRegular() * noOfWeekends < minCost) {
				minCost = hotelList.get(i).getWeekdayRoomRateRegular() * noOfWeekdays + hotelList.get(i).getWeekendRoomRateRegular() * noOfWeekends;
				for(int j = 0; j < cheapestHotelNameList.size(); j++) 
					cheapestHotelNameList.remove(j);
				cheapestHotelNameList.add(hotelList.get(i).getHotelName());
			}
			if(hotelList.get(i).getWeekdayRoomRateRegular() * noOfWeekdays + hotelList.get(i).getWeekendRoomRateRegular() * noOfWeekends == minCost)
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
		int minCost = hotelList.get(0).getWeekdayRoomRateRegular() * noOfWeekdays + hotelList.get(0).getWeekendRoomRateRegular() * noOfWeekends ;
		List<HotelDetails> cheapestHotelList = new ArrayList<>();
		cheapestHotelList.add(hotelList.get(0));
		//for and if used because we can have multiple hotels offering the same lowest total price until uc4
		for(int i = 1; i < hotelList.size(); i++) {
			if(hotelList.get(i).getWeekdayRoomRateRegular() * noOfWeekdays + hotelList.get(i).getWeekendRoomRateRegular() * noOfWeekends < minCost) {
				minCost = hotelList.get(i).getWeekdayRoomRateRegular() * noOfWeekdays + hotelList.get(i).getWeekendRoomRateRegular() * noOfWeekends;
				for(int j = 0; j < cheapestHotelList.size(); j++) 
					cheapestHotelList.remove(j);
				cheapestHotelList.add(hotelList.get(i));
			}
			if(hotelList.get(i).getWeekdayRoomRateRegular() * noOfWeekdays + hotelList.get(i).getWeekendRoomRateRegular() * noOfWeekends == minCost)
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
		int bestRatedCost = bestRatedHotel.getWeekdayRoomRateRegular() * noOfWeekdays + bestRatedHotel.getWeekendRoomRateRegular() * noOfWeekends;
		String bestRatedHotelInfo = bestRatedHotel.getHotelName() + ", Total Cost: $" + bestRatedCost;
		return bestRatedHotelInfo;
	}

	/**
	 * uc9 : get cheapest best rated hotel for rewards customer
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public String getCheapestBestRatedHotelForRewards (String startDate, String endDate) {
		String cheapestBestRatedHotelForRewardsInfo = null;
		try {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
			LocalDate startDateInput = LocalDate.parse(startDate, dateFormat);
			LocalDate endDateInput = LocalDate.parse(endDate, dateFormat);
			int noOfDaysToBook = (int) ChronoUnit.DAYS.between(startDateInput, endDateInput) + 1;
			List<DayOfWeek> daysList = new ArrayList<>();
			daysList = Stream.iterate(startDateInput.getDayOfWeek(), day -> day.plus(1)).limit(noOfDaysToBook).collect(Collectors.toList());
			int noOfWeekends = (int) daysList.stream().filter(day -> 
			day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)).count();
			int noOfWeekdays = daysList.size() - noOfWeekends;
			int minCost = hotelList.get(0).getWeekdayRoomRateRewards() * noOfWeekdays + hotelList.get(0).getWeekendRoomRateRewards() * noOfWeekends ;
			List<HotelDetails> cheapestHotelList = new ArrayList<>();
			cheapestHotelList.add(hotelList.get(0));
			//for and if used because we can have multiple hotels offering the same lowest total price until uc4
			for(int i = 1; i < hotelList.size(); i++) {
				if(hotelList.get(i).getWeekdayRoomRateRewards() * noOfWeekdays + hotelList.get(i).getWeekendRoomRateRewards() * noOfWeekends < minCost) {
					minCost = hotelList.get(i).getWeekdayRoomRateRewards() * noOfWeekdays + hotelList.get(i).getWeekendRoomRateRewards() * noOfWeekends;
					for(int j = 0; j < cheapestHotelList.size(); j++) 
						cheapestHotelList.remove(j);
					cheapestHotelList.add(hotelList.get(i));
				}
				if(hotelList.get(i).getWeekdayRoomRateRewards() * noOfWeekdays + hotelList.get(i).getWeekendRoomRateRewards() * noOfWeekends == minCost)
					cheapestHotelList.add(hotelList.get(i));
			}
			HotelDetails cheapestBestRatedHotelForRewards = cheapestHotelList.stream().max((hotelOne, hotelTwo) -> hotelOne.getRating() - hotelTwo.getRating()).orElse(null);
			cheapestBestRatedHotelForRewardsInfo = cheapestBestRatedHotelForRewards.getHotelName() + ", Rating: " + cheapestBestRatedHotelForRewards.getRating() + ", Total Cost: $" + minCost;
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			log.info("The format of dates(s) entered is incorrect!");
		}
		return cheapestBestRatedHotelForRewardsInfo;
	}
	
	public void typeOfCustomer(String typeOfCustomer) throws CustomerTypeException {
		if(CustomerType.REGULAR.name().equals(typeOfCustomer))
			this.typeOfCustomer = CustomerType.REGULAR;
		else if(CustomerType.REWARDS.name().equals(typeOfCustomer))
			this.typeOfCustomer = CustomerType.REWARDS;
		else throw new CustomerTypeException("Please enter only REGULAR or REWARDS!");
	}
	
	public String cheapestBestRatedHotelSelector(String startDate, String endDate) {
		if(typeOfCustomer == CustomerType.REGULAR)
			return getCheapestBestRatedHotel(startDate, endDate);
		else
			return getCheapestBestRatedHotelForRewards(startDate, endDate);
	}
	
	public static void main(String[] args) {
		log.info("Welcome to Hotel Reservation Program");
	}
}
