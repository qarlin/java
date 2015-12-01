package net.carlosu.java.patterns.factoryspring;

import javax.inject.Named;


@Named
public class ShortProcess implements ProcessType {

	@Override
	public String processMessage() {
		return "Short";
	}
}
