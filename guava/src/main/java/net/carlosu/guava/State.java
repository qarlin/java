package net.carlosu.guava;

import com.google.common.base.MoreObjects;

public class State {

	String code;
	String name;
	String region;
	double population;

	public State(String code, String name, String region, double population) {
		super();
		this.code = code;
		this.name = name;
		this.region = region;
		this.population = population;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("code", code).add("name", name).toString();
	}

}
