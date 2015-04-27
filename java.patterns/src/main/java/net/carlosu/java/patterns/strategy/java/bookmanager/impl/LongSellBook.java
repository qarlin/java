package net.carlosu.java.patterns.strategy.java.bookmanager.impl;

import net.carlosu.java.patterns.specification.generic.Specification;
import net.carlosu.java.patterns.specification.tradespecification.ShortNegativeSpecification;
import net.carlosu.java.patterns.specification.tradespecification.ShortPositionSpecification;
import net.carlosu.java.patterns.strategy.java.bookmanager.AbstractBookManager;
import net.carlosu.java.patterns.strategy.model.Trade;

public class LongSellBook extends AbstractBookManager {
	private Specification<Trade> shortPosition = new ShortPositionSpecification();
	private Specification<Trade> shortNegative = new ShortNegativeSpecification();
	
	public LongSellBook(){
		spec = shortPosition.and(shortNegative);
	}
	
	@Override
	public void assignBook(Trade trade) {
		// TODO Auto-generated method stub
		
	}

}
