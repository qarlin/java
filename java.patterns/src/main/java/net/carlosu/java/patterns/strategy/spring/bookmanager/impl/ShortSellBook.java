package net.carlosu.java.patterns.strategy.spring.bookmanager.impl;

import net.carlosu.java.patterns.specification.generic.Specification;
import net.carlosu.java.patterns.specification.tradespecification.ShortPositionSpecification;
import net.carlosu.java.patterns.strategy.model.Trade;
import net.carlosu.java.patterns.strategy.spring.bookmanager.BookManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortSellBook implements BookManager {
	@Autowired
	private ShortPositionSpecification shortPosition;

	@Override
	public void assignBook(Trade trade) {
		// TODO Auto-generated method stub
	}

	@Override
	public Specification<Trade> getSpecification() {
		return shortPosition;
	}

}
