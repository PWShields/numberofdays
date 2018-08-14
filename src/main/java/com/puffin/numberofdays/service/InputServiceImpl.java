package com.puffin.numberofdays.service;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.springframework.stereotype.Service;

@Service
public class InputServiceImpl implements InputService {


	CalculatorService calculatorService;

	TextIO textIO = TextIoFactory.getTextIO();

	public InputServiceImpl(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}

	@Override
	public void readDatesFromTerminal() {
		String minimumDate = "01/01/1901";
		String maximumDate = "31/12/2999";
		Integer numberOfDays = 0;

		String startDate = textIO.newStringInputReader()
				.withDefaultValue(minimumDate)
				.withPattern("[0-3][0-9](/)[0-2][1-9](/)[0-9]{4}")
				.withMaxLength(10)
				.withMinLength(10)
				.read("Start date:");

		String endDate = textIO.newStringInputReader()
				.withDefaultValue(maximumDate)
				.withPattern("[0-3][0-9](/)[0-2][1-9](/)[0-9]{4}")
				.withMaxLength(10)
				.withMinLength(10)
				.read("End date:");

		Boolean datesAreValid = validateDates(startDate, endDate);

		if (datesAreValid) {
			numberOfDays = calculatorService.calculateNumberOfDays(startDate, endDate);
			textIO.getTextTerminal().printf("Number of days is %d", numberOfDays);
			textIO.getTextTerminal().println("");
		} else {
			textIO.getTextTerminal().println("Dates supplied are NOT valid");
			textIO.getTextTerminal().println("");
			textIO.getTextTerminal().printf("Minimum date is %s", minimumDate);
			textIO.getTextTerminal().println("");
			textIO.getTextTerminal().printf("Maximum date is %s", maximumDate);
			textIO.getTextTerminal().println("");
			textIO.getTextTerminal().println("Values must be valid dates");
			textIO.getTextTerminal().println("Format is DD/MM/YYYY");
		}

		Boolean enterNewDates = askToContinue();
		if (enterNewDates) {
			readDatesFromTerminal();
		} else {
			sayGoodBye();
		}
	}

	private Boolean validateDates(String startDate, String endDate) {
		Boolean isValid = true;
		String[] start = startDate.split("/");
		String[] end = endDate.split("/");
		Integer startYear = Integer.parseInt(start[2]);
		Integer endYear = Integer.parseInt(end[2]);

		isValid = validateDays(Integer.parseInt(start[0]), Integer.parseInt(end[0]));
		if (isValid) {
			isValid = validateMonths(Integer.parseInt(start[1]), Integer.parseInt(end[1]));
		}
		if (isValid) {
			isValid = validateYears(startYear, endYear);
		}
		if (isValid && startYear == endYear) {
			isValid = validateSameYear(start, end);
		}
		return isValid;
	}

	private Boolean validateMonths(int startMonth, int endMonth) {
		return (startMonth >= 1 && startMonth <= 12 && endMonth >= 1 && endMonth <= 12);
	}

	private Boolean validateSameYear(String[] start, String[] end) {
		Boolean isValid = false;
		Integer startMonth = Integer.parseInt(start[1]);
		Integer endMonth = Integer.parseInt(end[1]);
		Integer startDay = Integer.parseInt(end[0]);
		Integer endDay = Integer.parseInt(end[0]);
		if (endMonth > startMonth) {
			isValid = true;
		} else if (endMonth == startMonth && startDay <= endDay) {
			isValid = true;
		}
		return isValid;
	}


	private Boolean validateYears(Integer startYear, Integer endYear) {
		Boolean isValid = endYear >= startYear;
		if (isValid) {
			if (endYear > 2999 || startYear < 1901)
				isValid = false;
		}
		return isValid;
	}

	private Boolean validateDays(Integer startDay, Integer endDay) {
		Boolean isValid = true;
		if (endDay < 1 ||
				endDay > 31 ||
				startDay < 1 ||
				startDay > 31) {
			isValid = false;
		}
		return isValid;
	}


	private boolean askToContinue() {

		Boolean enterNewDates = false;
		textIO.getTextTerminal().println("Do you wish to continue?");

		String enterNewDatesResponse = textIO.newStringInputReader()
				.withMaxLength(1)
				.withDefaultValue("N")
				.withIgnoreCase()
				.read("Y or N");

		if (enterNewDatesResponse.equalsIgnoreCase("Y")) {
			enterNewDates = true;
		}
		return enterNewDates;
	}


	private void sayGoodBye() {

		textIO.getTextTerminal().println("Thanks for playing, goodbye");

	}

}
