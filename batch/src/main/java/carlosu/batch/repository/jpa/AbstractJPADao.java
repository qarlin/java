package carlosu.batch.repository.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import carlosu.batch.repository.AbstractDao;

public class AbstractJPADao<T, ID> implements AbstractDao<T, ID> {

	private Class<T> entityClass;

	@PersistenceContext
	EntityManager entityManager;

	private CriteriaBuilder criteriaBuilder;
	private CriteriaQuery<T> criteriaQuery;
	private Root<T> root;

	@PostConstruct
	public void initIt() throws Exception{
		criteriaBuilder = entityManager.getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(this.entityClass);
		root = criteriaQuery.from(this.entityClass);
	}
	
	public AbstractJPADao(Class<T> entityClass){
		this.entityClass = entityClass;
	}

	@Override
	public T save(T entity) {
		entityManager.merge(entity);
		return entity;

	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entityManager.merge(entity));
	}

	@Override
	public void deleteById(ID id) {
		T object = entityManager.find(entityClass, id);
		if (object != null)
			delete(object);

	}

	@Override
	public T find(ID id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		criteriaQuery.select(root);
		TypedQuery<T> q = entityManager.createQuery(criteriaQuery);
		return q.getResultList();
	}

	@Override
	public T create(T entity) {
		entityManager.persist(entity);
		return entity;

	}
}
