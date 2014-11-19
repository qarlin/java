package com.hatumruna.taskmanager.ui.service;

import java.util.List;
import java.util.Map;

import com.hatumruna.taskmanager.domain.referencial.IReferenceObject;
import com.hatumruna.taskmanager.ui.forms.AbstractForm;

public interface FormService {
	public Map<String, List<? extends IReferenceObject>> loadReferentials(Class<? extends AbstractForm> form)  throws Exception;
	public Map<String, List<? extends IReferenceObject>> loadReferentials(String form)  throws Exception;
}
