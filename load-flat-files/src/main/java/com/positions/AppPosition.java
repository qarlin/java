package com.positions;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppPosition {

	public static void main(String[] args) {
		String[] springConfig = { "spring/batch/jobs/positions/job-read-positions.xml" };

		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("readMultiFileJob");

		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

		jobParametersBuilder.addString("dateTime", (new Date()).toString());
		jobParametersBuilder.addString("numberOfPartitions", "2");	
		jobParametersBuilder.addString("inputDataFileFormat", "orders_*.csv");
		
		JobParameters param = jobParametersBuilder.toJobParameters();
		
		try {

			JobExecution execution = jobLauncher.run(job, param);
			System.out.println("Exit Status : " + execution.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		((ClassPathXmlApplicationContext)context).close();
		System.out.println("Done");

	}

}
