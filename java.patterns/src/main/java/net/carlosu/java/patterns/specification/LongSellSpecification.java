package net.carlosu.java.patterns.specification;

import net.carlosu.java.patterns.strategy.Trade;

public class LongSellSpecification extends AbstractSpecification<Trade> {

	@Override
	public boolean isSatisfiedBy(Trade t) {
		return t != null;
	}
}
