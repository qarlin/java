package com.hatumruna.taskmanager.repository.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.hatumruna.taskmanager.domain.IDomainObject;
import com.hatumruna.taskmanager.repository.Repository;

public abstract class AbstractJPARepository<T extends IDomainObject> implements Repository<T> {
	private Class<T> entityClass;
	@PersistenceContext
	private EntityManager entityManager;

	private CriteriaBuilder criteriaBuilder;
	private CriteriaQuery<T> criteriaQuery;
	private Root<T> root;
	
	@PostConstruct
	public void initIt() throws Exception{
		criteriaBuilder = entityManager.getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(this.entityClass);
		root = criteriaQuery.from(this.entityClass);
	}
	
	public AbstractJPARepository(Class<T> entityClass){
		this.entityClass = entityClass;
	}
	
	@Override
	public T create(T object) {
		entityManager.persist(object);
		return object;
	}

	@Override
	public T save(T object) {
		entityManager.merge(object);
		return object;
		
	}

	@Override
	public void delete(T object) {
		entityManager.remove(entityManager.merge(object));
	}

	@Override
	public void deleteById(Long id) {
		T object = entityManager.find(entityClass, id);
		if (object != null)
			delete(object);
		
	}

	@Override
	public T find(Long id) {
		return entityManager.find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		criteriaQuery.select(root);
		TypedQuery<T> q = entityManager.createQuery(criteriaQuery);
		return q.getResultList();
		
	}

	@Override
	public List<T> findRange(int[] range) {
		criteriaQuery.select(root);
		TypedQuery<T> q = entityManager.createQuery(criteriaQuery);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	@Override
	public int count() {
		CriteriaQuery<Long> criteriaCount = criteriaBuilder.createQuery(Long.class);
		criteriaCount = criteriaCount.select(criteriaBuilder.count(root));
		TypedQuery<Long> q = entityManager.createQuery(criteriaCount);
		return ((Long) q.getSingleResult()).intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> search(String search){
		FullTextEntityManager fullTextEM = Search.getFullTextEntityManager(entityManager);
		QueryBuilder queryBuilder = fullTextEM.getSearchFactory().buildQueryBuilder().forEntity(entityClass).get();
		Query query = queryBuilder
				.keyword()
				.onFields(getSearchFields())
				.matching(search)
				.createQuery();
		javax.persistence.Query jpaQuery = fullTextEM.createFullTextQuery(query, entityClass);
		return jpaQuery.getResultList();
	}
	
	abstract protected String[] getSearchFields();
}
