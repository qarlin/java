package com.hatumruna.taskmanager.domain.business.scheduler;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;

import com.hatumruna.taskmanager.domain.IDomainObject;
import com.hatumruna.taskmanager.domain.lists.ExecutionStatus;

@Entity
public class ExecutionLog implements IDomainObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5777624964897466506L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private Date starDate;
	private Date endDate;
	@Enumerated
	private ExecutionStatus status;
	private String logFile;
	@ManyToOne()
	@JoinColumn(name="TASK_ID")
	@JsonBackReference
	private Task task;
	
	public Date getStarDate() {
		return starDate;
	}
	public void setStarDate(Date starDate) {
		this.starDate = starDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public ExecutionStatus getStatus() {
		return status;
	}
	public void setStatus(ExecutionStatus status) {
		this.status = status;
	}
	public String getLogFile() {
		return logFile;
	}
	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
}
