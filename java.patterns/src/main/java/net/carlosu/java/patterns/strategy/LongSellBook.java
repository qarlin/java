package net.carlosu.java.patterns.strategy;

import net.carlosu.java.patterns.specification.ShortPositionSpecification;

public class LongSellBook extends AbstractBookManager {
	
	public LongSellBook(){
		spec = new ShortPositionSpecification();
	}
	
	@Override
	public void assignBook(Trade trade) {
		// TODO Auto-generated method stub
		
	}

}
