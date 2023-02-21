package com.lukesukhanov.sbertask;

import static java.util.Comparator.comparing;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class App {

	private static final Path DATA_FILE_PATH = Path.of("Задача ВС Java Сбер.csv");
	private static final Charset DATA_FILE_CHARSET = Charset.forName("UTF-8");
	private static final String DATA_FILE_DELIMITER = ";";

	public static void main(String[] args) {
		List<City> cities = new ArrayList<>(1120);

		try (Scanner scanner = new Scanner(Files.newBufferedReader(DATA_FILE_PATH, DATA_FILE_CHARSET))) {
			while (scanner.hasNextLine()) {
				cities.add(City.getFromString(scanner.nextLine(), DATA_FILE_DELIMITER));
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// First way of sorting.
//		cities.sort(comparing((City city) -> city.getName().toLowerCase())
//				.reversed()); // В условии сказано "по убыванию", хотя в примере всё наоборот.

		// Second way of sorting.
//		cities.sort(comparing(City::getDistrict)
//				.thenComparing(comparing(City::getName))
//				.reversed());

//		PrintStream out = new PrintStream(System.out, true, DATA_FILE_CHARSET);
//		cities.forEach(out::println);
		
		if (cities.isEmpty()) {
			System.out.println("There is no cities to compare.");
			return;
		}
		
		City[] arrayOfCities = cities.toArray(City[]::new);
		int indOfMax = 0;
		int maxPopulation = arrayOfCities[0].getPopulation();
		for (int i = 0; i < arrayOfCities.length; i++) {
			if (arrayOfCities[i].getPopulation() > maxPopulation) {
				indOfMax = i;
				maxPopulation = arrayOfCities[i].getPopulation();
			}
		}
		
		DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setGroupingSeparator(' ');
		DecimalFormat decimalFormat = new DecimalFormat("#,###", decimalFormatSymbols);
		String formattedPopulation = decimalFormat.format(maxPopulation);
		
		System.out.format("[%d] = %s", indOfMax, formattedPopulation);
	}
}