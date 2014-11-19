package com.hatumruna.taskmanager.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hatumruna.taskmanager.domain.business.scheduler.Task;
import com.hatumruna.taskmanager.domain.referencial.IReferenceObject;
import com.hatumruna.taskmanager.domain.referencial.StatusType;
import com.hatumruna.taskmanager.exception.BusinessException;
import com.hatumruna.taskmanager.service.GenericCRUDService;
import com.hatumruna.taskmanager.service.TaskService;
import com.hatumruna.taskmanager.ui.forms.TaskBrowserForm;
import com.hatumruna.taskmanager.ui.forms.TaskEditForm;
import com.hatumruna.taskmanager.ui.service.FormService;

@Controller
@RequestMapping("/tasks")
public class TaskController {
	private TaskService taskService;
	private FormService formService;
	private GenericCRUDService genericService;
	
	@Inject
	public TaskController(TaskService taskSerivce, FormService formService, GenericCRUDService genericService){
		this.taskService = taskSerivce;
		this.formService = formService;
		this.genericService = genericService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getTasks(Model model) throws Exception{
		model.addAttribute("tasks", taskService.findAll());
		return "tasks";
	}
	
	@RequestMapping(value="search", method = RequestMethod.POST)
	public String searchTasks(Model model, String searchText) throws Exception{
		model.addAttribute("tasks", taskService.search(searchText));
		return "tasks";
	}
	
	@RequestMapping(value="add", method = RequestMethod.GET)
	public String newTask(Model model) throws Exception{
		//taskService.addTask(task);
		model.addAttribute("task", new Task());
		model.addAttribute("formaction", "add");
		addReferentials(model, formService.loadReferentials(TaskEditForm.class));
		return "formtask";
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	public String addTask(Model model,  @ModelAttribute Task task){
		task.setCreationDate(Calendar.getInstance().getTime());
		if (task.getStatus() != null && task.getStatus().getCode() != null){
			StatusType st = genericService.find(StatusType.class, task.getStatus().getCode());
			if (st != null)
				task.setStatus(st);
		} else {
			task.setStatus(null);
		}
		taskService.addTask(task);
		return "redirect:";
	}
	
	@RequestMapping(value="edit/{id}", method = RequestMethod.GET)
	public String editTask(Model model, @PathVariable Long id) throws Exception{
		model.addAttribute("task", taskService.find(id));
		model.addAttribute("formaction", "edit/" + id);
		addReferentials(model, formService.loadReferentials(TaskBrowserForm.class));
		return "formtask";
	}
	
	@RequestMapping(value="edit/{id}", method = RequestMethod.POST)
	public String editTask(Model model, @PathVariable Long id, @ModelAttribute Task task){
		// The new task has only the values modifies that are in the jsp form, others are null
		// Load the object and set the modified values.
		Task taskUpdated = taskService.find(id);
		taskUpdated.setName(task.getName());
		if (task.getStatus() != null && task.getStatus().getCode() != null){
			StatusType st = genericService.find(StatusType.class, task.getStatus().getCode());
			if (st != null)
				taskUpdated.setStatus(st);
		}
		taskService.save(taskUpdated);
		return "redirect:../";
	}
	
	@RequestMapping(value="delete/{id}")
	public String deleteTask(Model model, @PathVariable Long id){
		taskService.deleteById(id);
		return "redirect:../";
	}
	
	@RequestMapping("home")
	public String LoadHomePage(Model m){
		m.addAttribute("name", "Test");
		return "home";
	}
	
	@RequestMapping(value="execute/{id}", method = RequestMethod.GET)
	public String executeTask(Model model, @PathVariable Long id){
		try {
			taskService.executeTask(id);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return "redirect:../";
	}
	
	public Model addReferentials(Model model, Map<String, List< ? extends IReferenceObject>> map){
		for (String key : map.keySet()) {
			model.addAttribute(key, map.get(key));
		}
		return model;
	}
}
