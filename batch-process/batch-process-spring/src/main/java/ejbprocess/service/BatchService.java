package ejbprocess.service;

import java.util.Map;

import ejbprocess.dao.ContractDAO;

public interface BatchService {
	public void execute(Map<String, Object> context);
	public void setContractDAO(ContractDAO contractDAO);
}
