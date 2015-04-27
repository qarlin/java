package net.carlosu.java.patterns.strategy.java;

import net.carlosu.java.patterns.strategy.java.bookmanager.BookManager;
import net.carlosu.java.patterns.strategy.model.Trade;

public class BookServiceImpl implements BookService {

	@Override
	public void book(Trade trade) {
		BookManager manager = BookManagerFactory.getInstance().getManager(trade);
		manager.assignBook(trade);
	}

}
