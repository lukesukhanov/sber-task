package com.lukesukhanov.sbertask;

import static java.util.Comparator.comparing;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
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

		// First way of sort.
		cities.sort(comparing((City city) -> city.getName().toLowerCase())
				.reversed()); // В условии сказано "по убыванию", хотя в примере всё наоборот.

		// Second way of sort.
		cities.sort(comparing(City::getDistrict)
				.thenComparing(comparing(City::getName))
				.reversed());

		PrintStream out = new PrintStream(System.out, true, DATA_FILE_CHARSET);
		cities.forEach(out::println);
	}
}