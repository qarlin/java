package net.carlosu.java.patterns;

import org.junit.Test;

import net.carlosu.java.patterns.strategy.BookManager;
import net.carlosu.java.patterns.strategy.BookService;
import net.carlosu.java.patterns.strategy.BookServiceImpl;
import net.carlosu.java.patterns.strategy.LongSellBook;
import net.carlosu.java.patterns.strategy.ShortSellBook;
import net.carlosu.java.patterns.strategy.Trade;
import static org.junit.Assert.*;

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
