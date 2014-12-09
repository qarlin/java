package carlosu.batch.repository.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import carlosu.batch.repository.AbstractDao;

public abstract class AbstractJPADao<T, ID> implements AbstractDao<T, ID> {
	protected Class<T> entityClass;
	
	protected CriteriaBuilder criteriaBuilder;
	protected CriteriaQuery<T> criteriaQuery;
	protected Root<T> root;
	
	public abstract EntityManager getEntityManager();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AbstractJPADao(){
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = ((Class) type.getActualTypeArguments()[0]);
	}

	@Override
	public T save(T entity) {
		getEntityManager().merge(entity);
		return entity;

	}

	@Override
	public void delete(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	@Override
	public void deleteById(ID id) {
		T object = getEntityManager().find(entityClass, id);
		if (object != null)
			delete(object);

	}

	@Override
	public T find(ID id) {
		return getEntityManager().find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		criteriaQuery.select(root);
		TypedQuery<T> q = getEntityManager().createQuery(criteriaQuery);
		return q.getResultList();
	}

	@Override
	public T create(T entity) {
		getEntityManager().persist(entity);
		return entity;
	}
}
