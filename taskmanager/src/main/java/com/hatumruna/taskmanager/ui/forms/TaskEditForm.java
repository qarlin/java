package com.hatumruna.taskmanager.ui.forms;

import java.util.List;

import com.hatumruna.taskmanager.domain.referencial.IReferenceObject;
import com.hatumruna.taskmanager.domain.referencial.StatusType;

public class TaskEditForm extends AbstractEditForm {

	@Override
	public List<Class<? extends IReferenceObject>> getReferentials() {
		list.add(StatusType.class);
		return list;
	}
}
