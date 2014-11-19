package com.hatumruna.taskmanager.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hatumruna.taskmanager.domain.referencial.IReferenceObject;
import com.hatumruna.taskmanager.service.TaskService;
import com.hatumruna.taskmanager.ui.forms.TaskBrowserForm;
import com.hatumruna.taskmanager.ui.service.FormService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-context.xml")
public class TaskControllerTest {
	@Inject
	private TaskService taskService;
	@Inject
	private FormService formService;

	@Test
	public void testTaskServiceInjection() {
		assertNotNull(taskService);
		assertNotNull(formService);
	}
	
	@Test
	public void testTaskLoadReferentials() throws Exception{
		Map<String, List<? extends IReferenceObject>> map = formService.loadReferentials(TaskBrowserForm.class);
		assertNotNull(map);
		assertNotEquals(map.size(), 0);
	}

}
