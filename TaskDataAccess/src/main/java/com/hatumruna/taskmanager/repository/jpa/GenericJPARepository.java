package com.hatumruna.taskmanager.repository.jpa;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;
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
import com.hatumruna.taskmanager.repository.GenericRepository;

@Named("genericRepo")
public class GenericJPARepository implements GenericRepository{
	@PersistenceContext
	private EntityManager entityManager;
	private FullTextEntityManager fullTextEM;
	
	@PostConstruct
	public void init(){
		fullTextEM = Search.getFullTextEntityManager(entityManager);
	}
	@Override
	public <T extends IDomainObject> T create(T object) {
		entityManager.persist(object);
		return object;
	}

	@Override
	public <T extends IDomainObject> T save(T object) {
		entityManager.merge(object);
		return object;
	}

	@Override
	public <T extends IDomainObject> void delete(T object) {
		entityManager.remove(entityManager.contains(object) ? object : entityManager.merge(object));
	}

	@Override
	public <T extends IDomainObject> void deleteById(Class<T> clazz, Object id) {
		T object = entityManager.find(clazz, id);
		if (object != null)
			delete(object);
	}

	@Override
	public <T extends IDomainObject> T find(Class<T> clazz, Object id) {
		return entityManager.find(clazz, id);
	}

	@Override
	public <T extends IDomainObject> List<T> findAll(Class<T> clazz) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.select(root);
		TypedQuery<T> q = entityManager.createQuery(criteriaQuery);
		return q.getResultList();
	}

	@Override
	public <T extends IDomainObject> List<T> findRange(Class<T> clazz, int[] range) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		criteriaQuery.select(root);
		TypedQuery<T> q = entityManager.createQuery(criteriaQuery);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	@Override
	public <T extends IDomainObject> int count(Class<T> clazz) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		Root<T> root = criteriaQuery.from(clazz);
		CriteriaQuery<Long> criteriaCount = criteriaBuilder.createQuery(Long.class);
		criteriaCount = criteriaCount.select(criteriaBuilder.count(root));
		TypedQuery<Long> q = entityManager.createQuery(criteriaCount);
		return q.getSingleResult().intValue();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends IDomainObject> List<T> search(Class<T> clazz, String search, String[] fields){
		QueryBuilder queryBuilder = fullTextEM.getSearchFactory().buildQueryBuilder().forEntity(clazz).get();
		Query query = queryBuilder
				.keyword()
				.onFields(fields)
				.matching(search)
				.createQuery();
		javax.persistence.Query jpaQuery = fullTextEM.createFullTextQuery(query, clazz);
		return jpaQuery.getResultList();
	}
}
