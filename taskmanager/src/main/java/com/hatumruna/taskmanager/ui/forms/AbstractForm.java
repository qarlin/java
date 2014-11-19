package com.hatumruna.taskmanager.ui.forms;

import java.util.ArrayList;
import java.util.List;

import com.hatumruna.taskmanager.domain.referencial.IReferenceObject;

public abstract class AbstractForm {
	List<Class<? extends IReferenceObject>> list = new ArrayList<>();
	
	public abstract void init();
	public abstract void loadConfiguration();
	public abstract List<Class<? extends IReferenceObject>> getReferentials();
}
