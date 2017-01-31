package com.multithread;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class MakePartition implements Partitioner {

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		
		Map<String, ExecutionContext> executionContextMap = new HashMap<String, ExecutionContext>();
		
		for (int i = 1; i <= gridSize; i++) {
			
			ExecutionContext executionContext = new ExecutionContext();			
			executionContext.putString("threadId", "Thread_"+i);
			executionContext.putInt("partitionNo", i);			
			executionContextMap.put("Partition_"+i, executionContext);
		}
		
		return executionContextMap;
	}

}
