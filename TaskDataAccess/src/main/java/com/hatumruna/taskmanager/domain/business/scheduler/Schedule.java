package com.hatumruna.taskmanager.domain.business.scheduler;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

import com.hatumruna.taskmanager.domain.IDomainObject;

@Entity
public class Schedule implements IDomainObject {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String cron;
	private boolean notify;
	@ManyToOne()
	@JoinColumn(name="TASK_ID")
	@JsonBackReference
	private Task task;
	
	public String getCron() {
		return cron;
	}
	public void setCron(String cron) {
		this.cron = cron;
	}
	public boolean isNotify() {
		return notify;
	}
	public void setNotify(boolean notify) {
		this.notify = notify;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
		/*if (!task.getSchedules().contains(this))
			task.getSchedules().add(this);*/
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString(){
		return id + " - " + cron;
	}
}
