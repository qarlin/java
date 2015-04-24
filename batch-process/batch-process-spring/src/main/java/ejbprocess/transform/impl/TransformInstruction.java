package ejbprocess.transform.impl;

import java.util.Map;

import model.Contract;
import model.ContractDTO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import ejbprocess.transform.TransformData;

@Component
public class TransformInstruction implements TransformData<Contract, ContractDTO> {
	public static Logger logger = Logger.getLogger(TransformInstruction.class);
	
	@Override
	public ContractDTO transform(Contract s, Map<String, Object> context) {
		ContractDTO dto = new ContractDTO();
		dto.setId(s.getId());
		dto.setName(s.getName());
		return dto;
	}

}
