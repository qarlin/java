package net.carlosu.java.patterns.strategy;

public class BookServiceImpl implements BookService {

	@Override
	public void book(Trade trade) {
		BookManager manager = BookManagerFactory.getInstance().getManager(trade);
		manager.assignBook(trade);
	}

}
