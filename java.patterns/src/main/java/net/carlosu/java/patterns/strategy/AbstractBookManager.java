package net.carlosu.java.patterns.strategy;

import net.carlosu.java.patterns.specification.Specification;

public abstract class AbstractBookManager implements BookManager{

	protected Specification<Trade> spec;
		
	@Override
	public Specification<Trade> getSpecification(){
		return this.spec;
	}

}
