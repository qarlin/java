package net.carlosu.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Application Lifecycle Listener implementation class JobListener
 *
 */
@WebListener
public class JobListener implements ServletContextListener {
	Scheduler scheduler = null;

    /**
     * Default constructor. 
     */
    public JobListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("Context Destroyed");
        try {
                scheduler.shutdown();
        } 
        catch (SchedulerException e) {
                e.printStackTrace();
        }

    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	System.out.println("Context Initialized");
        
        try {
                // Setup the Job class and the Job group
        	JobDetail job = JobBuilder.newJob(JobQuartz.class)
    				.withIdentity("Job Name", "group1").build();

                // Create a Trigger that fires every 
                /*Trigger trigger = TriggerBuilder
            			.newTrigger()
            			.withIdentity("TriggerName", "group1")
            			.withSchedule(CronScheduleBuilder.cronSchedule("10 * * * * ?"))
            			.build();*/
                
                Trigger trigger = TriggerBuilder.newTrigger()
                        .withSchedule(
                                     SimpleScheduleBuilder.simpleSchedule()
                                     .withIntervalInSeconds(10)
                                     .repeatForever())
                                                   .build();
                
                // Setup the Job and Trigger with Scheduler & schedule jobs
                scheduler = new StdSchedulerFactory().getScheduler();
                scheduler.start();
                scheduler.scheduleJob(job, trigger);
        }
        catch (SchedulerException e) {
                e.printStackTrace();
        }

    }
	
}
