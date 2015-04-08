package ejbprocess.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ejb.ContractEJBRemote;
import ejbprocess.dao.ContractDAO;
import ejbprocess.model.Contract;
import ejbprocess.service.BatchService;
import ejbprocess.transform.TransformData;

@Component("CreateService")
public class CreateService implements BatchService {

	@Autowired
	private ContractDAO contractDAO;
	@Autowired
	private TransformData<Contract, Object> transfomrData;
	@Autowired
	private ContractEJBRemote remoteObject;
	
	@Override
	public void execute(Map<String, Object> context) {
		Date date = (Date) context.get("DATE");
		
		// Loading Data
		List<Contract> list = Collections.EMPTY_LIST;//contractDAO.getInstructions(date);
		
		// Transform Data
		List<Object> transformed = new ArrayList<Object>();
		for (Contract contract : list) {
			transformed.add(transfomrData.transform(contract, context));	
		}
		
		// Send EJB
		String result = remoteObject.create(transformed);
		
		// Transform Data
		
		// Save Data
		
	}

}
