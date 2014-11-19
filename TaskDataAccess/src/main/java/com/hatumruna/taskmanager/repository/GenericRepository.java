package com.hatumruna.taskmanager.repository;

import java.util.List;

import com.hatumruna.taskmanager.domain.IDomainObject;

public interface GenericRepository{
	public <T extends IDomainObject> T create(T object);
	public <T extends IDomainObject> T save(T object);
	public <T extends IDomainObject> void delete(T object);
	public <T extends IDomainObject> void deleteById(Class<T> clazz, Object id);
	
	public <T extends IDomainObject> T find(Class<T> clazz, Object id);
	public <T extends IDomainObject> List<T> findAll(Class<T> clazz);
	public <T extends IDomainObject> List<T> findRange(Class<T> clazz, int[] range);
	public <T extends IDomainObject> int count(Class<T> clazz);
	
	public <T extends IDomainObject> List<T> search(Class<T> clazz, String search, String[] fields);
}
