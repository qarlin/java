package factory.strategy;

import org.springframework.stereotype.Component;

import factory.model.Document;

@Component("A4Portrait")
public class PrintA4PortraitStrategy implements IPrintStrategy{

	@Override
	public void print(Document document) {
		System.out.println("Doing stuff to print an A4 portrait document");
	}

}
