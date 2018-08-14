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
			textIO.getTextTerminal().printf("Minimum date is %s", minimumDate);
			textIO.getTextTerminal().printf("Maximum date is %s", maximumDate);
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
		//endDate must be after startDate
		//days must be between 01 and 31, months must be between 01 and 12
		//years must be between 1901 and 2999
		return true;
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


//	TextIO textIO = TextIoFactory.getTextIO();
//
//	String startDate = textIO.newStringInputReader()
//
//			.withDefaultValue("01/01/1901")
//
//			.read("Start date:");
//
//	String endDate = textIO.newStringInputReader()
//
//			.withDefaultValue("31/12/2999")
//
//			.read("End date:");
//
//
//	int age = textIO.newIntInputReader()
//
//			.withMinVal(13)
//
//			.read("Age");
//
//	Month month = textIO.newEnumInputReader(Month.class)
//
//			.read("What month were you born in?");
//
//		textIO.getTextTerminal().printf("User %s is %d years old, was born in %s and has the password %s.\n",
//
//		                                user, age, month, password);

}
