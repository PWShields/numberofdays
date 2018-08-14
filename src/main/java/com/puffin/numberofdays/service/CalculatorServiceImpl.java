package com.puffin.numberofdays.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	private static final int BASE_YEAR = 1901;

	private static final Map<String, String> daysInMonth = createMap();

	private static Map<String, String> createMap() {
		Map<String, String> myMap = new HashMap<>();
		myMap.put("01", "31");
		myMap.put("02", "59");
		myMap.put("03", "90");
		myMap.put("04", "120");
		myMap.put("05", "151");
		myMap.put("06", "181");
		myMap.put("07", "212");
		myMap.put("08", "243");
		myMap.put("09", "273");
		myMap.put("10", "304");
		myMap.put("11", "334");
		myMap.put("12", "365");
		return myMap;
	}

	@Override
	public Integer calculateNumberOfDays(String startDate, String endDate) {
		Integer numberOfDays = 0;

		String[] start = startDate.split("/");
		String[] end = endDate.split("/");

		int numberOfDaysStart = calculateNumberFromBase(start);
		int numberOfDaysEnd = calculateNumberFromBase(end);
		numberOfDays = numberOfDaysEnd - numberOfDaysStart;

		return numberOfDays;
	}

	private int calculateNumberFromBase(String[] input) {
		int year = calculateNumberOfDaysFromYears(input[2]);
		int month = Integer.parseInt(daysInMonth.get(input[1]));
		int day = Integer.parseInt(input[0]);
		return year + month + day;
	}

	private int calculateNumberOfDaysFromYears(String startYear) {
		return 365 * (Integer.parseInt(startYear) - BASE_YEAR);
	}
}
