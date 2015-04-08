package net.carlosu.java.patterns.strategy;

import java.util.ArrayList;
import java.util.List;

public class BookManagerFactory {
	private static BookManagerFactory INSTANCE = null;

	private List<AbstractBookManager> bookManagers;
	
	private BookManagerFactory() {
	}

	public static BookManagerFactory getInstance() {
		if (INSTANCE == null) {
			// Thread Safe. Might be costly operation in some case
			synchronized (BookManagerFactory.class) {
				if (INSTANCE == null) {
					INSTANCE = new BookManagerFactory();
					INSTANCE.initialize();
				}
			}
		}
		return INSTANCE;
	}
	
	private void initialize() {
		bookManagers = new ArrayList<AbstractBookManager>();
		bookManagers.add(new LongSellBook());
		bookManagers.add(new ShortSellBook());
	}

	public BookManager getManager(Trade trade) {
		for (AbstractBookManager bookManager : bookManagers) {
			if (bookManager.getSpecification().isSatisfiedBy(trade))
				return bookManager;
		}
		return null;
	}
}
