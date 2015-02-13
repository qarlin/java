package factory.strategy;

import org.springframework.stereotype.Component;

import factory.model.Document;

@Component("A5Landscape")
public class PrintA5LandscapeStrategy implements IPrintStrategy{

	@Override
	public void print(Document document) {
		System.out.println("Doing stuff to print an A5 landscape document");
	}

}
