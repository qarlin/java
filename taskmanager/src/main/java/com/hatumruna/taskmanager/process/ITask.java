package com.hatumruna.taskmanager.process;

import com.hatumruna.taskmanager.exception.BusinessException;

public interface ITask {
	public void execute(String params, StringBuilder log) throws BusinessException;
}
