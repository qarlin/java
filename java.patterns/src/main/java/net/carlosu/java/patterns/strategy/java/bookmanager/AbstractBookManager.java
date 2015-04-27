package net.carlosu.java.patterns.strategy.java.bookmanager;

import net.carlosu.java.patterns.specification.generic.Specification;
import net.carlosu.java.patterns.strategy.model.Trade;

public abstract class AbstractBookManager implements BookManager{

	protected Specification<Trade> spec;
		
	@Override
	public Specification<Trade> getSpecification(){
		return this.spec;
	}

}
