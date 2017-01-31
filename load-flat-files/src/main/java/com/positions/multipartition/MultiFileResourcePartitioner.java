package com.positions.multipartition;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.core.io.Resource;

public class MultiFileResourcePartitioner implements Partitioner {
 
    private Resource inboundDir;
 
    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
 
        Map<String, ExecutionContext> partitionMap = new HashMap<String, ExecutionContext>();
        File dir;
		try {
			dir = inboundDir.getFile();
			if (dir.isDirectory()) {
	            File[] files = dir.listFiles();
	            for (File file : files) {
	                ExecutionContext context = new ExecutionContext();
	                context.put("fileResource", file.toURI().toString());
	                context.put("resultFileName", file.getName());
	                partitionMap.put(file.getName(), context);
	            }
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        return partitionMap;
    }
 
    public Resource getInboundDir() {
        return inboundDir;
    }
 
    public void setInboundDir(Resource inboundDir) {
        this.inboundDir = inboundDir;
    }
}
