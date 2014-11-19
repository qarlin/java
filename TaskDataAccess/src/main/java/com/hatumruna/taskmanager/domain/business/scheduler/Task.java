package com.hatumruna.taskmanager.domain.business.scheduler;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.hatumruna.taskmanager.domain.business.BusinessObject;

@Entity
@Indexed
public class Task extends BusinessObject{
	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	//private Long id;
	@Field
	private String name;
	@OneToMany(mappedBy="task", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<ExecutionLog> executionLogs;
	@OneToMany(mappedBy="task", cascade=CascadeType.ALL)
	@JsonManagedReference
	private List<Schedule> schedules;
	private String parameters;
	@Field
	private String taskProcess;
	
	public Task(){
		executionLogs = new ArrayList<>();
		schedules = new ArrayList<>();
	}
	/*public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ExecutionLog> getExecutionLogs() {
		return executionLogs;
	}
	public void setExecutionLogs(List<ExecutionLog> executionLogs) {
		this.executionLogs = executionLogs;
	}
	public void addExecutionsLog(ExecutionLog executionLog){
		if (this.executionLogs == null)
			this.executionLogs = new ArrayList<>();
		this.executionLogs.add(executionLog);
	}
	public List<Schedule> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
	public void addSchedule(Schedule schedule){
		if (this.schedules == null)
			this.schedules = new ArrayList<>();
		this.schedules.add(schedule);
		if (schedule.getTask() != this)
			schedule.setTask(this);
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(getUid() + " - " + name + "\n");
		return sb.toString();
	}
	public String getTaskProcess() {
		return taskProcess;
	}
	public void setTaskProcess(String taskProcess) {
		this.taskProcess = taskProcess;
	}
}
