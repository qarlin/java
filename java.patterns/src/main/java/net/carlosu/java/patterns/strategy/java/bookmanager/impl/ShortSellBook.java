package net.carlosu.java.patterns.strategy.java.bookmanager.impl;

import net.carlosu.java.patterns.specification.tradespecification.ShortPositionSpecification;
import net.carlosu.java.patterns.strategy.java.bookmanager.AbstractBookManager;
import net.carlosu.java.patterns.strategy.model.Trade;

public class ShortSellBook extends AbstractBookManager {
	
	public ShortSellBook() {
		spec = new ShortPositionSpecification();
	}

	@Override
	public void assignBook(Trade trade) {
		// TODO Auto-generated method stub

	}

}
