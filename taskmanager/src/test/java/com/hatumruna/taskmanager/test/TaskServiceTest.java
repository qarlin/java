package com.hatumruna.taskmanager.test;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hatumruna.taskmanager.domain.business.scheduler.Schedule;
import com.hatumruna.taskmanager.domain.business.scheduler.Task;
import com.hatumruna.taskmanager.domain.referencial.IReferenceObject;
import com.hatumruna.taskmanager.exception.BusinessException;
import com.hatumruna.taskmanager.process.CleanTempFolderTask;
import com.hatumruna.taskmanager.service.TaskService;
import com.hatumruna.taskmanager.ui.forms.TaskEditForm;
import com.hatumruna.taskmanager.ui.service.FormService;


public class TaskServiceTest {
	private static TaskService taskService;
	private static FormService formService;

	@SuppressWarnings("resource")
	@BeforeClass
	public static void init() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("jpa-context.xml");
		taskService = (TaskService) context.getBean("taskService");
		formService = (FormService) context.getBean("formService");
	}
	@Before
	public void setUp() throws Exception {
		
 	}
	
	@Test
	public void test() {
		Task t = new Task();
		t.setName("Tast 001");
		t.setTaskProcess(CleanTempFolderTask.class.getName());
		taskService.save(t);
		
		Task t2 = taskService.find(1L);
		try {
			taskService.executeTask(t2.getUid());
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		t2 = taskService.find(1L);
		Schedule s = new Schedule();
		s.setCron("* * * 30");
		s.setTask(t2);
		try {
			taskService.scheduleTask(s);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUIForm(){
		try {
			Map<String, List<? extends IReferenceObject>> map = formService.loadReferentials(TaskEditForm.class.getName());
			Assert.assertNotNull(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
