package net.carlosu.java.patterns.specification;

import net.carlosu.java.patterns.strategy.Trade;

public class ShortNegativeSpecification extends AbstractSpecification<Trade> {

	@Override
	public boolean isSatisfiedBy(Trade t) {
		return true;
	}

}
