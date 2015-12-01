package net.carlosu.java.patterns.factoryspring;

import org.springframework.stereotype.Component;


@Component
public class LongProcess implements ProcessType {

	@Override
	public String processMessage() {
		return "Long";
	}
}
