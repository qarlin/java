package net.carlosu.java.patterns.strategy.spring;

import java.util.List;

import net.carlosu.java.patterns.strategy.model.Trade;
import net.carlosu.java.patterns.strategy.spring.bookmanager.BookManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private List<BookManager> bookManagers;
	
	public BookManager getManager(Trade trade) {
		for (BookManager bookManager : bookManagers) {
			if (bookManager.getSpecification().isSatisfiedBy(trade))
				return bookManager;
		}
		return null;
	}
	
	@Override
	public void book(Trade trade) {
		BookManager manager = getManager(trade);
		manager.assignBook(trade);
	}
}
