package factory.strategy;

import factory.model.Document;

public interface IPrintStrategy {
	public void print(Document document);
}
