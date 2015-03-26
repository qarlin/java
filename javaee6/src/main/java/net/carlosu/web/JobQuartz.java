package net.carlosu.web;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobQuartz implements Job{
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Hello Quartz!");
		log.debug("Hello Quartz!");
	}

}
