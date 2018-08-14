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


		String startDate = textIO.newStringInputReader()
				.withDefaultValue("01/01/1901")
				.read("Start date:");

		String endDate = textIO.newStringInputReader()

				.withDefaultValue("31/12/2999")

				.read("End date:");

//		Integer numberOfDays = calculatorService.calculateNumberOfDays(startDate, endDate);
		Integer numberOfDays = 15;

		textIO.getTextTerminal().printf("Number of days is %d", numberOfDays);
		textIO.getTextTerminal().println("");

		Boolean enterNewDates = askToContinue();
		if (enterNewDates) {
			readDatesFromTerminal();
		} else {
			sayGoodBye();
		}
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
