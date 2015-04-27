package net.carlosu.java.patterns.strategy.java;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.carlosu.java.patterns.strategy.java.bookmanager.BookManager;
import net.carlosu.java.patterns.strategy.java.bookmanager.impl.LongSellBook;
import net.carlosu.java.patterns.strategy.java.bookmanager.impl.ShortSellBook;

public class BookManagerConfiguration {
	@Inject
	private LongSellBook longSellBook;
	@Inject
	private ShortSellBook shortSellBook;
	
	public void configuration(){
		List<BookManager> bookManagers = new ArrayList<BookManager>();
		bookManagers.add(longSellBook);
		bookManagers.add(shortSellBook);
		
		BookServiceAnnotation service = new BookServiceAnnotation();
		service.setBookManagers(bookManagers);
	}
}
