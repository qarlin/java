package carlosu.batch.repository.jpa;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class AbstractDb2JPADao<T, ID> extends AbstractJPADao<T, ID> {
	@PersistenceContext(unitName="tcontractUnit")
	EntityManager entityManager;
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PostConstruct
	public void initIt() throws Exception{
		criteriaBuilder = getEntityManager().getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(this.entityClass);
		root = criteriaQuery.from(this.entityClass);
	}
}
