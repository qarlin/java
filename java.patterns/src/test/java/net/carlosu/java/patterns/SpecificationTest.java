package net.carlosu.java.patterns;

import static org.junit.Assert.assertTrue;
import net.carlosu.java.patterns.specification.generic.Specification;
import net.carlosu.java.patterns.specification.tradespecification.LongSellSpecification;
import net.carlosu.java.patterns.specification.tradespecification.ShortNegativeSpecification;
import net.carlosu.java.patterns.specification.tradespecification.ShortPositionSpecification;
import net.carlosu.java.patterns.specification.tradespecification.ShortSpecification;
import net.carlosu.java.patterns.strategy.model.Trade;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class SpecificationTest {
	Logger logger = Logger.getLogger(SpecificationTest.class);
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void OneSpecificationTest() {
		LongSellSpecification spec = new LongSellSpecification();
		Trade trade = new Trade();
		Specification<Trade> longSell = spec;
		assertTrue(longSell.isSatisfiedBy(trade));
		ApplicationLog.audit("Log to the file.");
		logger.info("log in console");
	}
	
	@Test
	public void MoreSpecificationTest() {
		Trade trade = new Trade();
		ShortSpecification x1 = new ShortSpecification();
		ShortNegativeSpecification x2 = new ShortNegativeSpecification();
		ShortPositionSpecification x3 = new ShortPositionSpecification();
		
		Specification<Trade> specification = x1.and(x2).and(x3);
		assertTrue(specification.isSatisfiedBy(trade));
		
		Specification<Trade> specification2 = new ShortSpecification()
													.and(new ShortNegativeSpecification())
													.or(new ShortPositionSpecification());
		
		assertTrue(specification2.isSatisfiedBy(trade));
	}

}
