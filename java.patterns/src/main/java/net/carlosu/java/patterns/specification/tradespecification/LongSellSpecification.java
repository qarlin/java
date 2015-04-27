package net.carlosu.java.patterns.specification.tradespecification;

import org.springframework.stereotype.Component;

import net.carlosu.java.patterns.specification.generic.AbstractSpecification;
import net.carlosu.java.patterns.strategy.model.Trade;

@Component
public class LongSellSpecification extends AbstractSpecification<Trade> {

	@Override
	public boolean isSatisfiedBy(Trade t) {
		return t != null;
	}
}
