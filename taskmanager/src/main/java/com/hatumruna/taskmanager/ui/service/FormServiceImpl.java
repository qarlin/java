package com.hatumruna.taskmanager.ui.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hatumruna.taskmanager.domain.referencial.IReferenceObject;
import com.hatumruna.taskmanager.repository.GenericRepository;
import com.hatumruna.taskmanager.ui.forms.AbstractForm;

@Service("formService")
@Transactional
public class FormServiceImpl implements FormService {
	GenericRepository genericRepo;
	
	@Inject
	public FormServiceImpl(GenericRepository genericRepo){
		this.genericRepo = genericRepo;
	}
	@Override
	public Map<String, List<? extends IReferenceObject>> loadReferentials(
			Class<? extends AbstractForm> formClass) throws Exception {
		
		Map<String, List<? extends IReferenceObject>> map = new HashMap<>();
		AbstractForm form = formClass.newInstance();
		
		for (Class<? extends IReferenceObject> refClass : form.getReferentials()) {
			map.put(refClass.getSimpleName() + "s", genericRepo.findAll(refClass));
		}
		return map;
	}
	@Override
	public Map<String, List<? extends IReferenceObject>> loadReferentials(
			String form) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
