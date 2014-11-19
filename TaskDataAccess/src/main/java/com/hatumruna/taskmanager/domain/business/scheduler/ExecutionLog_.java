package com.hatumruna.taskmanager.domain.business.scheduler;

import com.hatumruna.taskmanager.domain.lists.ExecutionStatus;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-05-14T12:35:01.008-0400")
@StaticMetamodel(ExecutionLog.class)
public class ExecutionLog_ {
	public static volatile SingularAttribute<ExecutionLog, Long> id;
	public static volatile SingularAttribute<ExecutionLog, Date> starDate;
	public static volatile SingularAttribute<ExecutionLog, Date> endDate;
	public static volatile SingularAttribute<ExecutionLog, ExecutionStatus> status;
	public static volatile SingularAttribute<ExecutionLog, String> logFile;
	public static volatile SingularAttribute<ExecutionLog, Task> task;
}
