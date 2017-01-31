package com.partition;

import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;

public class OutputFileListener {
	private String outputKeyName = "outputFile";

	private String inputKeyName = "fileName";

	private String path = "file:./build/output/";
	
	public void setPath(String path) {
		this.path = path;
	}

	public void setOutputKeyName(String outputKeyName) {
		this.outputKeyName = outputKeyName;
	}

	public void setInputKeyName(String inputKeyName) {
		this.inputKeyName = inputKeyName;
	}

	@BeforeStep
	public void createOutputNameFromInput(StepExecution stepExecution) {
		ExecutionContext executionContext = stepExecution.getExecutionContext();
		String inputName = stepExecution.getStepName().replace(":", "-");
		if (executionContext.containsKey(inputKeyName)) {
			inputName = executionContext.getString(inputKeyName);
		}
		if (!executionContext.containsKey(outputKeyName)) {
			executionContext.putString(outputKeyName, path + FilenameUtils.getBaseName(inputName)
					+ ".csv");
		}
	}
}
