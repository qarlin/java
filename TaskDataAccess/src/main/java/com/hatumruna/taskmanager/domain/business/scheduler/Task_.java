package com.hatumruna.taskmanager.domain.business.scheduler;

import com.hatumruna.taskmanager.domain.business.BusinessObject_;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-11-24T14:40:20.175-0500")
@StaticMetamodel(Task.class)
public class Task_ extends BusinessObject_ {
	public static volatile SingularAttribute<Task, String> name;
	public static volatile ListAttribute<Task, ExecutionLog> executionLogs;
	public static volatile ListAttribute<Task, Schedule> schedules;
	public static volatile SingularAttribute<Task, String> parameters;
	public static volatile SingularAttribute<Task, String> taskProcess;
}
