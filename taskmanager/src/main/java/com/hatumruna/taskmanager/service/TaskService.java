package com.hatumruna.taskmanager.service;

import java.util.List;

import com.hatumruna.taskmanager.domain.business.scheduler.Schedule;
import com.hatumruna.taskmanager.domain.business.scheduler.Task;
import com.hatumruna.taskmanager.domain.query.TaskCriteria;
import com.hatumruna.taskmanager.exception.BusinessException;

public interface TaskService {
	public void executeTask(Long id) throws BusinessException;
	public void scheduleTask(Schedule s) throws BusinessException;
	
	public Task addTask(Task t);
	public Task save(Task t);
	public void delete(Task t);
	public void deleteById(Long id);
	
	public List<Task> findAll();
	public Task find(Long id);
	public List<Task> search(String search) throws BusinessException;
	public List<Task> search(TaskCriteria criteria) throws BusinessException;
}
