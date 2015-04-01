package net.carlosu.java.patterns.strategy;

import net.carlosu.java.patterns.specification.ShortNegativeSpecification;
import net.carlosu.java.patterns.specification.ShortSpecification;
import net.carlosu.java.patterns.specification.Specification;

public class BookManagerFactory {
	private ShortNegativeSpecification shortNegativeSpecification;
	private ShortSpecification shortSpecification;
	
	private static final BookManagerFactory INSTANCE = new BookManagerFactory();

	private BookManagerFactory() {
	}

	public static BookManagerFactory getInstance() {
		return INSTANCE;
	}
	public BookManager getManager(Trade trade) {
		Specification<Trade> short01 = shortNegativeSpecification.
									and(shortSpecification);
		
		Specification<Trade> short02 = shortNegativeSpecification.
									and(shortSpecification);
		
		if (short01.isSatisfiedBy(trade))
			return new LongSellBook();
		else if (short02.isSatisfiedBy(trade))
			return new ShortSellBook();
		else
			return null;
	}

}
