package net.carlosu.java.patterns.strategy.annotation;

import java.util.List;

import net.carlosu.java.patterns.strategy.BookManager;
import net.carlosu.java.patterns.strategy.BookService;
import net.carlosu.java.patterns.strategy.Trade;

public class BookServiceAnnotation implements BookService{
	List<BookManager> bookManagers;
	
	public void setBookManagers(List<BookManager> bookManagers){
		this.bookManagers = bookManagers;
	}
	
	@Override
	public void book(Trade trade) {
		BookManager manager = getManager(trade);
		manager.assignBook(trade);
	}
	
	private BookManager getManager(Trade trade) {
		for (BookManager bookManager : bookManagers) {
			if (bookManager.getSpecification().isSatisfiedBy(trade))
				return bookManager;
		}
		return null;
	}
}
