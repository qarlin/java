package com.hatumruna.taskmanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hatumruna.taskmanager.domain.business.scheduler.Task;
import com.hatumruna.taskmanager.exception.BusinessException;
import com.hatumruna.taskmanager.process.CleanTempFolderTask;
import com.hatumruna.taskmanager.service.TaskService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-context.xml")
//@TransactionConfiguration(defaultRollback=true)
//@Transactional
public class TaskServiceLuceneTest {
	@Inject
	private TaskService taskService;

	@Test
	public void testTaskServiceInjection() {
		assertNotNull(taskService);
	}
	
	@Test
	public void createTask(){
		Task taskExample = new Task();
		taskExample.setName("Test Task 999");
		Task result = taskService.addTask(taskExample);
		assertNotNull(result);
		assertNotNull(result.getUid());
	}
	
	@Test
	public void editTask() throws BusinessException{
		createTask();
		List<Task> list = taskService.search("999");
		Task taskExample = list.iterator().next();
		String name = "Test Task 999 to be modified";
		taskExample.setName(name);
		taskExample.setTaskProcess(CleanTempFolderTask.class.getName());
		taskService.save(taskExample);
		assertEquals(name, taskExample.getName());
		assertNotNull(taskExample.getTaskProcess());
	}
	
	@Test
	public void cc_findTaskCreated() throws BusinessException{
		createTask();
		List<Task> all = taskService.findAll();
		assertNotNull(all);
		assertNotEquals(0, all.size());
		
		List<Task> list = taskService.search("999");
		assertNotNull(list);
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void runTask() throws BusinessException{
		createTask();
		List<Task> list = taskService.search("999");
		Task taskExample = list.iterator().next();
		taskService.executeTask(taskExample.getUid());
		taskExample = taskService.find(taskExample.getUid());
		assertNotEquals(0, taskExample.getExecutionLogs().size());
	}
	
	@Test
	public void removeAllTest() throws BusinessException{
		createTask();
		List<Task> list = taskService.search("999");
		for (Task task : list) {
			taskService.delete(task);
		}
		list = taskService.search("999");
		assertEquals(0, list.size());
	}
}
