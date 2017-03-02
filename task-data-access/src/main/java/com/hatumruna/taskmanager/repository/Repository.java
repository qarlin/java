package com.hatumruna.taskmanager.repository;

import java.util.List;

import com.hatumruna.taskmanager.domain.IDomainObject;

public interface Repository <T extends IDomainObject>{
	public T create(T object);
	public T save(T object);
	public void delete(T object);
	public void deleteById(Long id);

	public T find(Long id);
	public List<T> findAll();
	public List<T> findRange(int[] range);
	public int count();
	
	public List<T> search(String search);
}
