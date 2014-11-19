package com.hatumruna.taskmanager.domain.business.scheduler;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-05-14T12:35:01.009-0400")
@StaticMetamodel(Schedule.class)
public class Schedule_ {
	public static volatile SingularAttribute<Schedule, Long> id;
	public static volatile SingularAttribute<Schedule, String> cron;
	public static volatile SingularAttribute<Schedule, Boolean> notify;
	public static volatile SingularAttribute<Schedule, Task> task;
}
