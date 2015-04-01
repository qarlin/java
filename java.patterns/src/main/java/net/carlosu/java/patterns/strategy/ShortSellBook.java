package net.carlosu.java.patterns.strategy;

import net.carlosu.java.patterns.specification.ShortPositionSpecification;

public class ShortSellBook extends AbstractBookManager {
	
	public ShortSellBook() {
		spec = new ShortPositionSpecification();
	}

	@Override
	public void assignBook(Trade trade) {
		// TODO Auto-generated method stub

	}

}
