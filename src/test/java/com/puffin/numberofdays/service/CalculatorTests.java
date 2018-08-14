package com.puffin.numberofdays.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatorTests {

	CalculatorService calculatorService;

	private static Stream<Arguments> buildTestData() {
		return Stream.of(
				Arguments.of("02/06/1983", "22/06/1983", 19),
				Arguments.of("04/07/1984", "25/12/1984", 173),
				Arguments.of("03/01/1989", "03/08/1983", 1979)
		);
	}

	@BeforeEach
	public void setup() {
		calculatorService = new CalculatorServiceImpl();

	}

	@ParameterizedTest
	@MethodSource("buildTestData")
	public void givenValidInput_thenValidOutput(String start, String end, Integer expectedResult) {

		Integer numberOfDays = calculatorService.calculateNumberOfDays(start, end);

		assertThat(numberOfDays, is(expectedResult));

	}

	//		Input       (DD/MM/YYYY)      Output (Days)
//		02/06/1983  22/06/1983          19
//		04/07/1984  25/12/1984          173
//      03/01/1989  03/08/1983          1979


}
