package com.hatumruna.taskmanager.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hatumruna.taskmanager.domain.business.scheduler.Task;
import com.hatumruna.taskmanager.domain.referencial.IReferenceObject;
import com.hatumruna.taskmanager.service.TaskService;
import com.hatumruna.taskmanager.ui.service.FormService;

@Controller
@RequestMapping("/crudtasks")
public class TaskCRUDController {
	private TaskService taskService;
	private FormService formService;
	
	@Inject
	public TaskCRUDController(TaskService taskSerivce, FormService formService){
		this.taskService = taskSerivce;
		this.formService = formService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Task> getTasks(Model model){
		return taskService.findAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public @ResponseBody Task getTask(Model model, @PathVariable Long id){
		return taskService.find(id);
	}
	
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public @ResponseBody Task createTask(@PathVariable Long id, @RequestBody Task task){
		taskService.save(task);
		return task;
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.PUT)
	public @ResponseBody Task saveTask(@PathVariable Long id, @RequestBody Task task){
		taskService.save(task);
		return task;
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody void deleteTask(@PathVariable Long id){
		taskService.deleteById(id);
	}
	
	@RequestMapping(value="/ref/{form}", method = RequestMethod.GET)
	public @ResponseBody Map<String, List<? extends IReferenceObject>> getReferentials(@PathVariable String form) throws Exception{
		return formService.loadReferentials(form);
	}
}
