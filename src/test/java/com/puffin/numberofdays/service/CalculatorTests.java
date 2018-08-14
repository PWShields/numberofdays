package com.puffin.numberofdays.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class CalculatorTests {

	CalculatorService calculatorService;
	private String start;
	private String end;
	private Integer expectedResult;

	public CalculatorTests(String start, String end, Integer expectedResult) {
		this.start = start;
		this.end = end;
		this.expectedResult = expectedResult;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{"02/06/1983", "22/06/1983", 19},
				{"04/07/1984", "25/12/1984", 173},
				{"03/01/1989", "03/08/1983", 1979}
		});
	}

	@Before
	public void setup() {
		calculatorService = new CalculatorServiceImpl();
	}

	@Test
	public void givenValidInput_thenValidOutput() {
		Integer numberOfDays = calculatorService.calculateNumberOfDays(start, end);
		assertThat(numberOfDays, is(expectedResult));
	}

	@After
	public void tearDown() {
		calculatorService = null;
	}

}
