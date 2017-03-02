package com.hatumruna.taskmanager.repository.jpa;

import javax.inject.Named;

import com.hatumruna.taskmanager.domain.business.scheduler.Task;
import com.hatumruna.taskmanager.repository.TaskRepository;

@Named("taskRepo")
public class TaskJPARepositoryImpl extends AbstractJPARepository<Task> implements
		TaskRepository {
	private String[] fields = {"name","taskProcess"};
	
	public TaskJPARepositoryImpl() {
		super(Task.class);
	}
	
	@Override
	protected String[] getSearchFields(){
		return fields;
	}
}
