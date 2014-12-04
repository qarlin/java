package carlosu.dao;

import java.util.List;

public interface AbstractDao<T, ID> {
	public T find(ID id);
	public List<T> findAll();
	
	public T save(T entity);
	public T create(T entity);
	public void delete(T entity);
	public void deleteById(ID id);
}
