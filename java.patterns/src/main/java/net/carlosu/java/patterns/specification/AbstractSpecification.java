package net.carlosu.java.patterns.specification;

public abstract class AbstractSpecification<T> implements Specification<T> {
	
	@Override
	public Specification<T> and(final Specification<T> specification) {
		return new AndSpecification<T>(this, specification);
	}

	@Override
	public Specification<T> or(final Specification<T> specification) {
		return new OrSpecification<T>(this, specification);
	}

	@Override
	public Specification<T> not(final Specification<T> specification) {
		return new NotSpecification<T>(specification);
	}
}
