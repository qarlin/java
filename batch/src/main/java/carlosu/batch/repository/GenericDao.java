package carlosu.batch.repository;

import java.util.List;

public interface GenericDao {
	public <T> T create(T object);
	public <T> T save(T object);
	public <T> void delete(T object);
	public <T> void deleteById(Class<T> clazz, Object id);
	
	public <T> T find(Class<T> clazz, Object id);
	public <T> List<T> findAll(Class<T> clazz);
}
