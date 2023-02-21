package com.lukesukhanov.sbertask;

import java.io.Serializable;
import java.util.Objects;

public final class City implements Serializable {

	private static final long serialVersionUID = 123L;

	private final String name;
	private final String region;
	private final String district;
	private final int population;
	private final String foundation;

	public City(String name, String region, String district, int population, String foundation) {
		this.name = name;
		this.region = region;
		this.district = district;
		this.population = population;
		this.foundation = foundation;
	}

	public static City getFromString(String str, String delimiter) {
		String[] tokens = str.split(delimiter);

		String name = null;
		String region = null;
		String district = null;
		int population = -1;
		String foundation = null;

		try {
			name = tokens[1];
			region = tokens[2];
			district = tokens[3];
			try {
				population = Integer.parseInt(tokens[4]);
			} catch (NumberFormatException ignore) {
			}
			foundation = tokens[5];
		} catch (ArrayIndexOutOfBoundsException ignore) {
		}

		return new City(name, region, district, population, foundation);
	}

	public String getName() {
		return name;
	}

	public String getRegion() {
		return region;
	}

	public String getDistrict() {
		return district;
	}

	public int getPopulation() {
		return population;
	}

	public String getFoundation() {
		return foundation;
	}

	@Override
	public int hashCode() {
		int hashCode = Objects.hashCode(name);
		hashCode = 31 * hashCode + Objects.hashCode(region);
		hashCode = 31 * hashCode + Objects.hashCode(district);
		hashCode = 31 * hashCode + Integer.hashCode(population);
		hashCode = 31 * hashCode + Objects.hashCode(foundation);
		return hashCode;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (!(o instanceof City)) {
			return false;
		}

		City that = (City) o;
		return name.equals(that.name) && region.equals(that.region) && district.equals(that.district)
				&& population == that.population && foundation.equals(that.foundation);
	}

	@Override
	public String toString() {
		return String.format("City{name='%s', region='%s', district='%s', population=%d, foundation='%s'}", name,
				region, district, population, foundation);
	}
}