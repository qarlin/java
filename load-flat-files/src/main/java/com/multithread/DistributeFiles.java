package com.multithread;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

public class DistributeFiles implements Tasklet, InitializingBean{
	private Resource dataDirectory;
	private int numberOfPartitions;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(dataDirectory, "directory must be set");
	}

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {

		File filesDirectory = dataDirectory.getFile();
		File tmp = new File(filesDirectory.getAbsolutePath()+File.separator+"tmp");

		// Clean/Make directory
		if (tmp.exists() && tmp.isDirectory())
			FileUtils.cleanDirectory(tmp);
		else
			FileUtils.forceMkdir(tmp);
		
	
		// Make folder for each partition to read data from 
		for(int i =0; i< numberOfPartitions; i++)
		{
			String fileForpartition = tmp.getAbsolutePath()+File.separator+(i+1);
			FileUtils.forceMkdir(new File(fileForpartition));
		}
				
		File[] dataFiles = filesDirectory.listFiles();		
				
		int counter = 1;
		for(File dataFile: dataFiles){
			
			if(!dataFile.isDirectory()){
			int partitionfolderNumber = counter%numberOfPartitions;
			partitionfolderNumber =	(partitionfolderNumber != 0) ? partitionfolderNumber: numberOfPartitions;
			String path = tmp.getAbsolutePath()+File.separator+partitionfolderNumber+File.separator;
			
			File dest = new File(path+dataFile.getName());
			FileUtils.copyFile(dataFile, dest);
			
			counter++;
			}
		}
		
		return RepeatStatus.FINISHED;
	}

	public void setDataDirectory(Resource dataDirectory) {
		this.dataDirectory = dataDirectory;
	}

	public void setNumberOfPartitions(String numberOfPartitions) {
		this.numberOfPartitions = Integer.parseInt(numberOfPartitions);
	}
	
	
}
