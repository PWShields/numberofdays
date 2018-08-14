package com.puffin.numberofdays;

import com.puffin.numberofdays.service.InputServiceImpl;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NumberofdaysApplication implements CommandLineRunner {

	@Autowired
	InputServiceImpl inputService;

	public static void main(String[] args) {
		//Set up a Terminal Window
		TextIO textIO = TextIoFactory.getTextIO();
		textIO.getTextTerminal().println("Welcome to Number of Days");
		textIO.getTextTerminal().println("");
		textIO.getTextTerminal().println("Please enter a start date and an end date when prompted");
		textIO.getTextTerminal().println("Format is DD/MM/YYYY");
		textIO.getTextTerminal().println("Minimum date is 01/01/1901");
		textIO.getTextTerminal().println("Maximum date is 31/12/2999");

		SpringApplication app = new SpringApplication(NumberofdaysApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		inputService.readDatesFromTerminal();
	}
}
