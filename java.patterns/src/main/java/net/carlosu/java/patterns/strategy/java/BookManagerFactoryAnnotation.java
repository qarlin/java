package net.carlosu.java.patterns.strategy.java;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.carlosu.java.patterns.strategy.java.bookmanager.AbstractBookManager;
import net.carlosu.java.patterns.strategy.java.bookmanager.BookManager;
import net.carlosu.java.patterns.strategy.java.bookmanager.impl.LongSellBook;
import net.carlosu.java.patterns.strategy.java.bookmanager.impl.ShortSellBook;
import net.carlosu.java.patterns.strategy.model.Trade;

public class BookManagerFactoryAnnotation {
	private static BookManagerFactoryAnnotation INSTANCE = null;

	@Inject
	private LongSellBook longSellBook;
	@Inject
	private ShortSellBook shortSellBook;
	
	private List<AbstractBookManager> bookManagers;
	
	private BookManagerFactoryAnnotation() {
	}

	public static BookManagerFactoryAnnotation getInstance() {
		if (INSTANCE == null) {
			// Thread Safe. Might be costly operation in some case
			synchronized (BookManagerFactoryAnnotation.class) {
				if (INSTANCE == null) {
					INSTANCE = new BookManagerFactoryAnnotation();
					INSTANCE.initialize();
				}
			}
		}
		return INSTANCE;
	}
	
	private void initialize() {
		bookManagers = new ArrayList<AbstractBookManager>();
		bookManagers.add(longSellBook);
		bookManagers.add(shortSellBook);
	}

	public BookManager getManager(Trade trade) {
		for (AbstractBookManager bookManager : bookManagers) {
			if (bookManager.getSpecification().isSatisfiedBy(trade))
				return bookManager;
		}
		return null;
	}
}
