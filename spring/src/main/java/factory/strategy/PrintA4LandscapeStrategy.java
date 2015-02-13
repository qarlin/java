package factory.strategy;

import org.springframework.stereotype.Component;

import factory.model.Document;

@Component("A4Landscape")
public class PrintA4LandscapeStrategy implements IPrintStrategy{

	@Override
	public void print(Document document) {
		System.out.println("Doing stuff to print an A4 landscape document");
	}

}
