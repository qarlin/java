package com.hatumruna.taskmanager.service;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hatumruna.taskmanager.domain.business.scheduler.ExecutionLog;
import com.hatumruna.taskmanager.domain.business.scheduler.Schedule;
import com.hatumruna.taskmanager.domain.business.scheduler.Task;
import com.hatumruna.taskmanager.domain.lists.ExecutionStatus;
import com.hatumruna.taskmanager.domain.query.TaskCriteria;
import com.hatumruna.taskmanager.exception.BusinessException;
import com.hatumruna.taskmanager.process.CleanTempFolderTask;
import com.hatumruna.taskmanager.process.ITask;
import com.hatumruna.taskmanager.repository.GenericRepository;
import com.hatumruna.taskmanager.repository.TaskRepository;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {
	@Inject
	TaskRepository taskRepo;
	@Inject
	GenericRepository genericRepo;
	
	@Override
	public List<Task> findAll() {
		List<Task> list = taskRepo.findAll();
		for (Task task : list) {
			List<Schedule> s = task.getSchedules();
			task.setSchedules(s);
		}
		return list;
	}

	@Override
	public Task addTask(Task t) {
		return taskRepo.create(t);
		
	}

	@Override
	public Task save(Task t) {
		return taskRepo.save(t);
	}

	@Override
	public void delete(Task t) {
		taskRepo.delete(t);
	}
	
	@Override
	public void deleteById(Long id) {
		taskRepo.deleteById(id);
	}

	@Override
	public Task find(Long id) {
		return taskRepo.find(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void executeTask(Long id) throws BusinessException {
		Task t = find(id);
		if (t != null)
			t.setTaskProcess(CleanTempFolderTask.class.getName());
		
		ExecutionLog log = new ExecutionLog();
		StringBuilder sb = new StringBuilder();
		
		
		ITask taskProcess;
		try {
			Class<ITask> taskClass = (Class<ITask>) Class.forName(t.getTaskProcess());
			
			taskProcess = taskClass.newInstance();
			
			log.setStarDate(Calendar.getInstance().getTime());
			taskProcess.execute(t.getParameters(), sb);
			log.setTask(t);
			log.setStatus(ExecutionStatus.OK);
			
		} catch (BusinessException be) {
			sb.append("ERROR: " + be.getLocalMessage());
			log.setStatus(ExecutionStatus.ERROR);
		} catch (Exception e) {
			sb.append("ERROR: " + e.getStackTrace());
			log.setStatus(ExecutionStatus.ERROR);
		} finally {
			log.setEndDate(Calendar.getInstance().getTime());
		}
		log.setLogFile(sb.toString());
		genericRepo.create(log);
	}

	@Override
	public void scheduleTask(Schedule s) throws BusinessException {
		genericRepo.create(s);
	}

	@Override
	public List<Task> search(String search) throws BusinessException {
		if (search == null || search.isEmpty())
			return taskRepo.findAll();
		return taskRepo.search(search);
	}

	@Override
	public List<Task> search(TaskCriteria criteria) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
