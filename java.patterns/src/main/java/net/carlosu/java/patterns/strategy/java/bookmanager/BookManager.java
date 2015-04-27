package net.carlosu.java.patterns.strategy.java.bookmanager;

import net.carlosu.java.patterns.specification.generic.Specification;
import net.carlosu.java.patterns.strategy.model.Trade;

public interface BookManager {
	Specification<Trade> getSpecification();
	void assignBook(Trade trade);

}
