package com.hatumruna.taskmanager.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hatumruna.taskmanager.domain.IDomainObject;
import com.hatumruna.taskmanager.repository.GenericRepository;

@Service("genericCRUDService")
@Transactional
public class GenericCRUDServiceImpl implements GenericCRUDService {
	@Inject
	GenericRepository genericRepo;
	
	@Override
	public <T extends IDomainObject> T  create(T object) {
		genericRepo.create(object);
		return object;
	}

	@Override
	public <T extends IDomainObject> T save(T object) {
		genericRepo.save(object);
		return object;
	}

	@Override
	public <T extends IDomainObject> void delete(T object) {
		genericRepo.delete(object);
	}

	@Override
	public <T extends IDomainObject> void deleteById(Class<T> clazz, Object id) {
		genericRepo.deleteById(clazz, id);
	}

	@Override
	public <T extends IDomainObject> T find(Class<T> clazz, Object id) {
		return genericRepo.find(clazz, id);
	}

	@Override
	public <T extends IDomainObject> List<T> findAll(Class<T> clazz) {
		return genericRepo.findAll(clazz);
	}

	@Override
	public <T extends IDomainObject> List<T> findRange(Class<T> clazz, int[] range) {
		return genericRepo.findRange(clazz, range);
	}

	@Override
	public <T extends IDomainObject> int count(Class<T> clazz) {
		return genericRepo.count(clazz);
	}

	@Override
	public <T extends IDomainObject> List<T> search(Class<T> clazz, String search, String[] fields) {
		return genericRepo.search(clazz, search, fields);
	}
}
