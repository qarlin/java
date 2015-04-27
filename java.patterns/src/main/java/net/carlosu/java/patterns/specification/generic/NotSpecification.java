package net.carlosu.java.patterns.specification.generic;


public class NotSpecification<T> extends AbstractSpecification<T> {
	private Specification<T> spec1;
	
	public NotSpecification(Specification<T> spec1) {
		this.spec1 = spec1;
	}
	@Override
	public boolean isSatisfiedBy(T t) {
		return !this.spec1.isSatisfiedBy(t);
	}


}
