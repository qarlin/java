package net.carlosu.java.patterns.strategy;

import net.carlosu.java.patterns.specification.Specification;

public interface BookManager {
	Specification<Trade> getSpecification();
	void assignBook(Trade trade);

}
