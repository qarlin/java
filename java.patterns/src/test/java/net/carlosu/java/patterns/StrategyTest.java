package net.carlosu.java.patterns;

import static org.junit.Assert.assertNotNull;
import net.carlosu.java.patterns.strategy.java.BookService;
import net.carlosu.java.patterns.strategy.java.BookServiceImpl;
import net.carlosu.java.patterns.strategy.java.bookmanager.BookManager;
import net.carlosu.java.patterns.strategy.java.bookmanager.impl.LongSellBook;
import net.carlosu.java.patterns.strategy.java.bookmanager.impl.ShortSellBook;
import net.carlosu.java.patterns.strategy.model.Trade;

import org.junit.Test;

public class StrategyTest{

	@Test
	public void StrategyObjectTest(){
		BookService bookService = new BookServiceImpl();
		Trade trade = new Trade();
		bookService.book(trade);
		assertNotNull(bookService);
		assertNotNull(trade);
	}
	
	@Test
	public void BookManagerTest(){
		Trade trade = new Trade();
		
		BookManager bookManager = new LongSellBook();
		bookManager.assignBook(trade);
		assertNotNull(bookManager);
		
		bookManager = new ShortSellBook();
		bookManager.assignBook(trade);
		assertNotNull(bookManager);
	}
}
