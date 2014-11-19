package com.hatumruna.taskmanager.process;

import com.hatumruna.taskmanager.exception.BusinessException;

public class CleanTempFolderTask implements ITask {

	@Override
	public void execute(String params, StringBuilder log)
			throws BusinessException {
		log.append("Task Clean Temp Folder Executed");

	}

}
